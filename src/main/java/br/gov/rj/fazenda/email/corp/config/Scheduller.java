package br.gov.rj.fazenda.email.corp.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.gov.rj.fazenda.email.corp.jms.ListenerApp;


/**
 * @description Classe responsável por atualizar data de fechamento randomico do sistema.
 * @author Rebeque
 */
@Configuration
@EnableScheduling
public class Scheduller implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ListenerApp listener;
	
	// @Scheduled(fixedDelay = 60000)	// 15s
	public void onReceiverQueue() {
		System.out.println("Ler Fila !");
		listener.ReceiverQueue();
		
	}	

	
}