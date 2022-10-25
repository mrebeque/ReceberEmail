package br.gov.rj.fazenda.email.corp.jms;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.gov.rj.fazenda.email.corp.service.EmailService;
import br.gov.rj.fazenda.email.corp.vo.Email;

@Component
public class ListenerApp { 
	
	   private JmsTemplate jmsTemplate;
	   
		@Value("${email.corp.mq.queue}")	
		private String fila;   
		
		@Value("${email.corp.file_dir}")
		private String fileDir;
		
		@Autowired
		private EmailService emailService;
		
	    @Autowired
	    public ListenerApp(JmsTemplate jmsTemplate) {
			this.jmsTemplate = jmsTemplate;
	    }	    
		
	    @JmsListener(destination = "${email.corp.mq.queue}")
	    public void onReceiverQueue(Email email ) {
	    	emailService.sendMail(email);
	    }

	    public void ReceiverQueue() {
			Object objMsg = jmsTemplate.receiveAndConvert(fila);
			if (objMsg == null)
				return;
			  
			if ((objMsg instanceof Email) == false) {
				System.out.println("Não é um email válido");
				return;
			}
			Email email = (Email) objMsg;
			HashMap<String, byte[]> arquivos = email.getArquivos();
	        if (arquivos.isEmpty() == false ) {	        	
	        	for (Map.Entry<String, byte[]> arquivo : arquivos.entrySet()) {	        		
					try {
					 
					 Path path = Paths.get(fileDir + FileSystems.getDefault().getSeparator() + arquivo.getKey());
						Files.write(path, arquivo.getValue());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	        	
	        }
			/*
			for (int i = 0; i < email.getAnexos().getArquivo().size(); i++) {
				Path path = Paths.get(fileDir +"/anexo-"+i+".pdf");
				try {
					Files.write(path, email.getAnexos().getArquivo().get(i));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			*/
	    }
}
