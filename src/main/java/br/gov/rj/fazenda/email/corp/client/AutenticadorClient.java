package br.gov.rj.fazenda.email.corp.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.gov.rj.fazenda.email.corp.dto.CredencialDTO;

@Component
public class AutenticadorClient {
	
	
	@Value("${auth.token-autenticacao}")
	private String tokenAutenticacao;
	
	@Value("${auth.baseUrl}")
	private String baseUrlAuth;
	
	CredencialDTO credencial = new CredencialDTO();
	
	
	private static final String SUFFIX_AUTH = "/api/v1/usuario/auttenticar";
	private static final String SUFFIX_KEY = "/api/v1/aplicacao/obterChaveJWT";

	private String chaveJwt;
	
	private RestTemplate templateAutorizacao;

	private RestTemplate createRestTemplate(String token) {
		return new RestTemplateBuilder(rt-> rt.getInterceptors().add((request, body, execution) -> {
	        request.getHeaders().add("Authorization", "Bearer "+token);	        
	        return execution.execute(request, body);
	    })).build();
	}
	
	
	@PostConstruct
	public void init( ) {
		this.autenticar();
		this.obterChaveJwst();
	}
	private void autenticar() {		
		 templateAutorizacao = this.createRestTemplate(tokenAutenticacao);
		 this.credencial = templateAutorizacao
				 			.getForObject(this.baseUrlAuth + SUFFIX_AUTH, 
				            	         CredencialDTO.class);	
	}
	
	private void obterChaveJwst( ) {
		 if (credencial.getStatus().equals("OK")) {
			 this.chaveJwt  = templateAutorizacao
					 				.getForObject(this.baseUrlAuth + SUFFIX_KEY,
		            		         String.class);
		 }
	}
	
	public String getChaveJwt() {
		return this.chaveJwt;
	}
}