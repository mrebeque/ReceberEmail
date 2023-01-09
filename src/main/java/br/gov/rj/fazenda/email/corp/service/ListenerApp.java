package br.gov.rj.fazenda.email.corp.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.gov.rj.fazenda.email.corp.dto.EmailDTO;

@Component
public class ListenerApp { 
	
	   private JmsTemplate jmsTemplate;
	   
		@Value("${email.corp.mq.queue}")	
		private String fila;   
		
	    @Autowired
	    public ListenerApp(JmsTemplate jmsTemplate) {
			this.jmsTemplate = jmsTemplate;
	    }	    
		
	    @PostConstruct
	    public void init() {
	    	System.out.println("====");
	    	System.out.println(fila);
	    	System.out.println("====");
	    }
	    
	    @JmsListener(destination = "${email.corp.mq.queue}")
	    public void onReceiverQueue(EmailDTO email ) {
	    	System.out.println("====  email ====");
	    	System.out.println(email.getFrom());
	    	System.out.println(email.getSubject());
	    	System.out.println("====");

	    }

	    
//	    public void ReceiverQueue() {
//			Object objMsg = jmsTemplate.receiveAndConvert(fila);
//			if (objMsg == null)
//				return;
//			  
//			if ((objMsg instanceof Email) == false) {
//				System.out.println("Não é um email válido");
//				return;
//			}
//			Email email = (Email) objMsg;
//			HashMap<String, byte[]> arquivos = email.getArquivos();
//	        if (arquivos.isEmpty() == false ) {	        	
//	        	for (Map.Entry<String, byte[]> arquivo : arquivos.entrySet()) {	        		
//					try {
//					 
//					 Path path = Paths.get(fileDir + FileSystems.getDefault().getSeparator() + arquivo.getKey());
//						Files.write(path, arquivo.getValue());
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//	        	
//	        }
//	    }
}
