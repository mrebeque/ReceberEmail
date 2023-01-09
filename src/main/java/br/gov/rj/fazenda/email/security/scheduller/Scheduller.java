package br.gov.rj.fazenda.email.security.scheduller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.rj.fazenda.email.security.client.AutenticadorClient;
import lombok.extern.log4j.Log4j2;

/**
 * @author Rebeque
 */

@Log4j2
@Configuration
@EnableScheduling
public class Scheduller implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	AutenticadorClient authClient;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Scheduled(fixedDelay = 18000000)	// valor 5M
	public void atualizarListaClientes()  {
		authClient.renovarTokenAcesso();
		log.info("Token de acesso renovado!");


	}	
	
}