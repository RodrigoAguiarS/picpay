package br.com.rodrigo.picpay.repository;

import br.com.rodrigo.picpay.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

    Page<Transacao> findByOrigem_LoginOrDestino_Login(String loginOrigem, String loginDestino, Pageable paginacao);


}
