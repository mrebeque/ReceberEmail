package br.gov.rj.fazenda.email.corp.controller.impl;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.rj.fazenda.email.corp.controller.EmailResourceApi;
import br.gov.rj.fazenda.email.corp.dto.EmailDTO;
import br.gov.rj.fazenda.email.corp.exception.Result;
import br.gov.rj.fazenda.email.corp.service.MensageriaService;

@RestController
@RequestMapping("/api/v1/email")
public class EmailControllerApi implements EmailResourceApi {
	
	@Autowired 
	private MensageriaService msgSrv;

	@Override
	@PostMapping(value = "/enviar")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADM')")
	public ResponseEntity<String> enviar(
			  @RequestBody EmailDTO email) throws URISyntaxException {
		
		
		Result result = msgSrv.enviarMensagem(email);
		if (result.hasErro()) {
			System.out.println(result.getMessage().toString());
			return ResponseEntity
					.badRequest()
					.body(result.getMessage().toString());
		}
		   
		return  ResponseEntity
				.ok()
				.body("Email enviado para fila de distribuição.");
	}
	
}
