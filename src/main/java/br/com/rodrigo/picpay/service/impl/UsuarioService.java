package br.com.rodrigo.picpay.service.impl;

import br.com.rodrigo.picpay.conversor.UsuarioConversor;
import br.com.rodrigo.picpay.dto.UsuarioDTO;
import br.com.rodrigo.picpay.exception.NegocioException;
import br.com.rodrigo.picpay.model.Transacao;
import br.com.rodrigo.picpay.model.Usuario;
import br.com.rodrigo.picpay.repository.UsuarioRepository;
import br.com.rodrigo.picpay.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConversor usuarioConversor;

    @Override
    public UsuarioDTO consultar(String login) {
        Usuario usuario = consultarEntidade(login);
        return usuarioConversor.converterEntidadeParaDto(usuario);
    }

    @Override
    @Transactional
    public Usuario consultarEntidade(String login) {
        return usuarioRepository.findByLogin(login);
    }

    @Override
    @Async("asyncExecutor")
    @Transactional
    public void atualizarSaldo(Transacao transacaoSalva, Boolean isCartaoCredito) {
        decrementarSaldo(transacaoSalva, isCartaoCredito);
        incrementarSaldo(transacaoSalva);
    }

    private void incrementarSaldo(Transacao transacaoSalva) {
        usuarioRepository.updateIncrementarSaldo(transacaoSalva.getDestino().getLogin(), transacaoSalva.getValor());
    }

    private void decrementarSaldo(Transacao transacaoSalva, Boolean isCartaoCredito) {
        if (!isCartaoCredito) {
            usuarioRepository.updateDecrementarSaldo(transacaoSalva.getOrigem().getLogin(), transacaoSalva.getValor());
        }
    }

    @Override
    public void validar(Usuario... usuarios) {

        Arrays.asList(usuarios).stream().forEach(usuario -> {
            if (usuario == null) {
                throw new NegocioException(MensagemValidacao.ERRO_USUARIO_INEXISTENTE);
            }

        });
    }

    @Override
    public List<UsuarioDTO> listar(String login) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioConversor.converterEntidadesParaDtos(
                usuarios.stream().filter(v -> !v.getLogin().equals(login)).collect(Collectors.toList()));
    }

}
