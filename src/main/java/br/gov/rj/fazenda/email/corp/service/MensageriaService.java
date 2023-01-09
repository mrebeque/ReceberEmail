package br.gov.rj.fazenda.email.corp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Map;

import org.python.icu.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import br.gov.rj.fazenda.email.corp.dto.EmailDTO;

@Service
public class MensageriaService {
	
	   private JmsTemplate jmsTemplate;
	   
		@Value("${email.corp.mq.queue}")	
		private String fila;   

		@Value("${app.dirAnexos}")
		private String dirAnexos;
		
	    @Autowired
	    public MensageriaService(JmsTemplate jmsTemplate) {
			this.jmsTemplate = jmsTemplate;
	    }
	    public void enviarMensagem(EmailDTO email){
	    	SimpleDateFormat ds = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	    	
            email.setTipoEmail("HTML");
            email.setDataEnvio(ds.format(Calendar.getInstance().getTime()));
            email.setStatus("oK");
            email.setError("Sem erro");
            email.setPastaAnexos("");
	    	if (email.getArquivos().isEmpty() == false) {
	    		
	    		Path path = Paths.get(dirAnexos + File.separator + email.getSistema() + "_" + Calendar.getInstance().getTimeInMillis());	    		
	    	    boolean existeDir = (new File(path.toUri()).mkdirs());	    	    
	    		email.setPastaAnexos(path.toFile().getName());
	    		for (Map.Entry<String, String> listaArquivos : email.getArquivos().entrySet()) {	        		
					try {
						byte[] arquivo = Base64.getDecoder().decode(listaArquivos.getValue());						
						Files.write(Paths.get(path + path.getFileSystem().getSeparator() + listaArquivos.getKey()), arquivo);
					} catch (IOException e) {
						e.printStackTrace();
					}
	    		}	
	    		email.getArquivos().clear();
		    }
	    	try {
	    		
	    		System.out.println("=====================");
	    		System.out.println("Enviando para fila");	    		
	    		System.out.println("=====================");
	    		jmsTemplate.convertAndSend(fila, email);	
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    }    	
}
