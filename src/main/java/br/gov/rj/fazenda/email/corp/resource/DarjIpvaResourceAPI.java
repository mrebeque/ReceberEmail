package br.gov.rj.fazenda.email.corp.resource;


import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;




@Tag(name = "Darj Ipva")
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

public interface DarjIpvaResourceAPI {
    @Operation(summary = "Obtém todos os tipos logradouros")
    ResponseEntity<byte[]> getBoleto();

}
