package br.com.rodrigo.picpay.service;

import br.com.rodrigo.picpay.dto.TransacaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITransacaoService {

    TransacaoDTO processar(TransacaoDTO transacaoDTO);

    Page<TransacaoDTO> listar(Pageable paginacao, String usuario);

}
