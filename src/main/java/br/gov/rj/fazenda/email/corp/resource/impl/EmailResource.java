package br.gov.rj.fazenda.email.corp.resource.impl;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.rj.fazenda.email.corp.resource.EmailResourceApi;
import br.gov.rj.fazenda.email.corp.service.EmailService;
import br.gov.rj.fazenda.email.corp.vo.Email;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1/email")
public class EmailResource implements EmailResourceApi {
	
	@Autowired
	private EmailService emailSrv;
	
	
	
	/**
	 * {@code GET /api/cliente : obter todos os clientes }
	 * 
	 * @return 
	 */
	@Override
	@PostMapping("/enviar")
	public ResponseEntity<Email> enviar(Email email) throws URISyntaxException {
		
		emailSrv.sendMail(email);
		return new ResponseEntity<Email>(HttpStatus.OK);
	}

}
