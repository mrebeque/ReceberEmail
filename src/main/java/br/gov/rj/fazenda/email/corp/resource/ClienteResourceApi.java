package br.gov.rj.fazenda.email.corp.resource;

import java.net.URISyntaxException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.gov.rj.fazenda.email.corp.dto.ClienteEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.ClienteSaidaDTO;
import br.gov.rj.fazenda.email.corp.exception.RecursoNaoEncontrado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cliente")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
        @ApiResponse(responseCode = "400", description = "Bad Request: Parâmetro informado é inválido."),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado."),
        @ApiResponse(responseCode = "403", description = "Usuário sem permissão."),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor."),
        @ApiResponse(responseCode = "503", description = "Erro comunicação gatway."),
        @ApiResponse(responseCode = "504", description = "Serviço inexistente.")
})
public interface ClienteResourceApi {

    @Operation(summary = "Obtém todos os clientes")
    ResponseEntity<Page<ClienteSaidaDTO>> pesquisar(ClienteEntradaDTO entrada, @PageableDefault Pageable pageable);

    @Operation(summary = "Obtém cliente por id")
    ResponseEntity<ClienteSaidaDTO> load(@PathVariable Long id) throws RecursoNaoEncontrado;

    @Operation(summary = "Cria um novo cliente")
    ResponseEntity<ClienteSaidaDTO> incluir(@RequestBody ClienteEntradaDTO entradaDTO) throws URISyntaxException;

    @Operation(summary = "Atualiza um cliente")
    ResponseEntity<ClienteSaidaDTO> atualizar(@RequestBody ClienteEntradaDTO entradaDTO) throws RecursoNaoEncontrado;

    @Operation(summary = "Exclui um cliente")
    ResponseEntity<Void> excluir(@PathVariable Long id) throws RecursoNaoEncontrado;
}
