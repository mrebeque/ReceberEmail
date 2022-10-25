package br.gov.rj.fazenda.email.corp.resource;

import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.gov.rj.fazenda.email.corp.vo.Email;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Email")
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
public interface EmailResourceApi {
    @Operation(summary = "Enviar Email para fila!")
    ResponseEntity<Email> enviar(@RequestBody Email email) throws URISyntaxException;
}
