package br.gov.rj.fazenda.email.corp.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.gov.rj.fazenda.email.corp.dto.EmailDTO;

//@Component
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
	    
//	    @JmsListener(destination = "${email.corp.mq.queue}")
//	    public void onReceiverQueue(EmailDTO email ) {
//	    	System.out.println("====  email ====");
//	    	System.out.println(email.getFrom());
//	    	System.out.println(email.getSubject());
//	    	System.out.println("====");
//
//	    }


	    // @JmsListener(destination = "${email.corp.mq.queue}", containerFactory = "myFactory")
	    public void onReceiverQueue(EmailDTO email ){
	    	System.out.println("====  email ====");
	    	System.out.println(email.getFrom());
	    	System.out.println(email.getSubject());
	    	System.out.println("====");
	    }
	    
}
