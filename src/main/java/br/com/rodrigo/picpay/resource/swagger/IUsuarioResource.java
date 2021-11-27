package br.com.rodrigo.picpay.resource.swagger;

import br.com.rodrigo.picpay.dto.ErrorDTO;
import br.com.rodrigo.picpay.dto.UsuarioDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Api(value = "/usuarios",  description = "Operações relacionadas a Usuários")
public interface IUsuarioResource {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Saldo consultado com sucesso", response = UsuarioDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado") })
    @GetMapping("/{login}/saldo")
    public ResponseEntity<UsuarioDTO> consultarSaldo(@PageableDefault(page = 0, size = 20) Pageable paginacao,
                                                     @PathVariable String login);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contatos encontrado com sucesso", response = UsuarioDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrados") })
    @GetMapping("/contatos")
    public ResponseEntity<List<UsuarioDTO>> listar(@RequestParam String login);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário encontrado com sucesso", response = UsuarioDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrada") })
    @GetMapping("/{login}")
    public ResponseEntity<UsuarioDTO> consultar(@PathVariable String login);

}
