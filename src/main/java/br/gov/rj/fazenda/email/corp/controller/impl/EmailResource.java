package br.gov.rj.fazenda.email.corp.controller.impl;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.rj.fazenda.email.corp.controller.EmailResourceApi;
import br.gov.rj.fazenda.email.corp.dto.EmailDTO;
import br.gov.rj.fazenda.email.corp.service.MensageriaService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1/email")
public class EmailResource implements EmailResourceApi {
	
	@Autowired 
	private MensageriaService msgSrv;
	
	
	/**
	 * {@code GET /api/cliente : obter todos os clientes }
	 * 
	 * @return 
	 */
	@Override
	@PostMapping(value = "/enviar")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADM') or hasAuthority('SRV')")
	public ResponseEntity<String> enviar(
			  @RequestBody EmailDTO email) throws URISyntaxException {
		log.info("Recebendo Solicitação de Email do usuário:" + email.getFrom());
		msgSrv.enviarMensagem(email);
		return  ResponseEntity
				.ok()
				.body("Email enviando para fila de distribuição.");
	}
	
}
