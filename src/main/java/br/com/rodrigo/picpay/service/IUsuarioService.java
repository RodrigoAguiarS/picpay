package br.com.rodrigo.picpay.service;

import br.com.rodrigo.picpay.dto.UsuarioDTO;
import br.com.rodrigo.picpay.model.Transacao;
import br.com.rodrigo.picpay.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario consultarEntidade(String login);

    UsuarioDTO consultar(String login);

    void atualizarSaldo(Transacao transacaoSalva, Boolean isCartaoCredito);

    void validar(Usuario... usuarios);

    List<UsuarioDTO> listar(String login);

}
