package br.com.rodrigo.picpay.resource;

import br.com.rodrigo.picpay.dto.TransacaoDTO;
import br.com.rodrigo.picpay.service.ITransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;




@RestController
@RequestMapping("/transacoes")
public class TransacaoResource extends ResourceBase<TransacaoDTO> {

    @Autowired
    private ITransacaoService transacaoService;

    @PostMapping
    @CacheEvict(cacheNames = "Transacoes", allEntries = true)
    public ResponseEntity<TransacaoDTO> salvar(@RequestBody @Valid TransacaoDTO transacaoDTO,
                                               UriComponentsBuilder uriBuilder) {
        TransacaoDTO transacaoRetornoDTO = transacaoService.processar(transacaoDTO);
        String path = "/transacoes/{codigo}";
        return responderItemCriadoComURI(transacaoRetornoDTO, uriBuilder, path, transacaoRetornoDTO.getCodigo());
    }

    @GetMapping
    @Cacheable(cacheNames = "Transacoes", key = "#root.method.name")
    public Page<TransacaoDTO> listar(@PageableDefault(page = 0, size = 20) Pageable paginacao,
                                     @RequestParam String login) {
        Page<TransacaoDTO> transacoes = transacaoService.listar(paginacao, login);
        return transacoes;
    }
}
