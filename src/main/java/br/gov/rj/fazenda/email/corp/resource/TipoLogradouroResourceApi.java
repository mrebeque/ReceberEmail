package br.gov.rj.fazenda.email.corp.resource;

import java.net.URISyntaxException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroDTO;
import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroFiltroDTO;
import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroFormDTO;
import br.gov.rj.fazenda.email.corp.exception.RecursoNaoEncontrado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tipo Logradouro")
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
public interface TipoLogradouroResourceApi {

    @Operation(summary = "Obtém todos os tipos logradouros")
    ResponseEntity<Page<TipoLogradouroDTO>> pesquisar(TipoLogradouroFiltroDTO filtro, @PageableDefault Pageable pageable);

    @Operation(summary = "Obtém tipo logradouro por id")
    ResponseEntity<TipoLogradouroDTO> load(@PathVariable Long id) throws RecursoNaoEncontrado;

    @Operation(summary = "Cria um tipo logradouro")
    ResponseEntity<TipoLogradouroDTO> incluir(@RequestBody TipoLogradouroFormDTO tipoLogradouroFormDTO) throws URISyntaxException;

    @Operation(summary = "Altera um tipo logradouro")
    ResponseEntity<TipoLogradouroDTO> atualizar(@RequestBody TipoLogradouroFormDTO tipoLogradouroFormDTO) throws RecursoNaoEncontrado;

}
