package br.gov.rj.fazenda.email.security.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
	
	private static final String SUFFIX_AUTH = "/api/v1/usuario/autenticar";
	private static final String SUFFIX_KEY = "/api/v1/aplicacao/obterChaveJWT";
	private static final String SUFFIX_EXPIRACAO = "/api/v1/usuario/obterDataHoraExpiracao";
	private static final String SUFFIX_RENOVAR = "/api/v1/usuario/renovarToken";
	
	private String chaveJwt;	
	private RestTemplate templateAutorizacao;
	private RestTemplate templateAcesso;
	private Date dataHOraExpiracao;

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
		try {
		 templateAutorizacao = this.createRestTemplate(tokenAutenticacao);
		 this.credencial = templateAutorizacao
				 			.postForObject(this.baseUrlAuth + SUFFIX_AUTH,
				 						null, 
				            	        CredencialDTO.class);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void obterChaveJwst( ) {
		 if (credencial.getStatus().equals("OK")) {
			 templateAcesso = this.createRestTemplate(credencial.getToken());
			 this.chaveJwt  = templateAcesso
					 				.getForObject(this.baseUrlAuth + SUFFIX_KEY,
		            		         String.class);
		 }
	}
	
	private LocalDate obterDataHoraExpiracao( ) {
		 LocalDate result = null;
		 if (credencial.getStatus().equals("OK")) {
			 templateAcesso = this.createRestTemplate(credencial.getToken());
			 String dataHora = templateAcesso
					 				.getForObject(this.baseUrlAuth + SUFFIX_EXPIRACAO,
		            		         String.class);
					
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
			
			result = LocalDate.parse(dataHora, formato);
		 }
		return result;
	}

	public void renovarTokenAcesso( ) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		LocalDate data = LocalDate.parse("23/11/2015", formato); 
		System.out.println(data);
		 if (credencial.getStatus().equals("OK")) {
			 templateAcesso = this.createRestTemplate(credencial.getToken());
			 this.credencial = templateAcesso
					 				.postForObject(this.baseUrlAuth + SUFFIX_RENOVAR,
					 				null, 
					 				CredencialDTO.class);
		 }
	}

	public String getChaveJwt() {
		return this.chaveJwt;
	}
}