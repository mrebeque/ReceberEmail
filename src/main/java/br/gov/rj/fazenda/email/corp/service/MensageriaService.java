package br.gov.rj.fazenda.email.corp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import br.gov.rj.fazenda.email.corp.dto.EmailDTO;
import br.gov.rj.fazenda.email.corp.exception.Result;
import br.gov.rj.fazenda.email.corp.exception.SefazException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MensageriaService {
	
	   private JmsTemplate jmsTemplate;
	   
		@Value("${email.corp.mq.queue}")	
		private String fila;   

		@Value("${app.dirAnexos}")
		private String dirAnexos;
		
		@Value("${spring.profiles.active}")
		private String ambienteAPP;
		
		@Value("${email.corp.ambienteProdutivo}")
		private String ambienteProdutivo;
		
		@Value("${email.corp.dominio}")
		private String dominioSefaz;
		
		private Result result = new Result();
		
		private final int dominioEmail = 1;
		
	    @Autowired
	    public MensageriaService(JmsTemplate jmsTemplate) {
			this.jmsTemplate = jmsTemplate;
	    }
	    
		public Result enviarMensagem(EmailDTO email){
			result.clean();
			result.setTimestamp(System.currentTimeMillis());
			result.setStatus(true);
	    	try {
				
		    	SimpleDateFormat ds = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		    	
	            email.setTipoEmail("HTML");
	            email.setDataEnvio(ds.format(Calendar.getInstance().getTime()));
	            email.setStatus("oK");
	            email.setError("Sem erro");
	            email.setPastaAnexos("");
	            
	            if (!this.isDestinatariosValido(email)) 
	            	return result;
	            
		    	if (!this.baixarAnexos(email))
		    		return result;
			    
		    	if (!this.isProvedorValido(email))
		    		return result;
		    	
		    	jmsTemplate.convertAndSend(fila, email);
	            

	    	} catch (SefazException e) {
				result.informarErro(e.getLocalizedMessage());
				
			} finally {
	    		email.getArquivos().clear();
			}
			return result;
			
    		
	    } 
	    
	    private boolean baixarAnexos(EmailDTO email) {
	    	boolean status = true;

	    	if (email.getArquivos().isEmpty()) 
	    		return status;
	    	
    		Path path = Paths.get(dirAnexos + File.separator + email.getSistema() + "_" + Calendar.getInstance().getTimeInMillis());	    		
    		File dir = new File(path.toUri());
    		dir.mkdirs();
    		email.setPastaAnexos(path.toFile().getName());
    		for (Map.Entry<String, String> listaArquivos : email.getArquivos().entrySet()) {
    			
				try {
					byte[] arquivo = Base64.getDecoder().decode(listaArquivos.getValue());						
					Files.write(Paths.get(path + path.getFileSystem().getSeparator() + listaArquivos.getKey()), arquivo);
					
				} catch (IOException e) {

					status = false;					
					result.informarErro("Erro para baixar o anexo " + listaArquivos.getValue());
					
					email.setStatus("Error");
					email.setError("Erro ao gravar enexo: " + listaArquivos.getKey());
					
					log.error("Erro ao gravar enexo: " + listaArquivos.getKey());
					log.error(e.getMessage());					
				}
    		}
			return status;    		
	    }
	    
	    private boolean isDestinatariosValido(EmailDTO email) {
	    	boolean status = true;
	    	EmailValidator validadorEmail = EmailValidator.getInstance();
	    	String [] listaFrom = email.getFrom().split(";");
	    	for (String emailDestino : listaFrom) {
				if (!validadorEmail.isValid(emailDestino.trim())) {
					status = false;
					result.informarErro(emailDestino + ": Email Inválido");					
				}
	    	}
	    	
	    	String [] listacopia = email.getCopia().split(";");
	    	for (String emailDestino : listacopia) {
				if (!validadorEmail.isValid(emailDestino)) {
					status = false;
					result.informarErro(emailDestino + ": Email Inválido");					
				}
	    	}

	    	
	    	return status;	    	
	    }
	    
	    private boolean isProvedorValido(EmailDTO email) {
	    	boolean status = true;
	    	boolean isAmbienteNaoProdutivo = (!ambienteAPP.equals(ambienteProdutivo)); 
	    	boolean isDominioSEFAZ = false;
	    	String dominio = null;
	    	
	    	String [] listaFrom = email.getFrom().split(";");
	    	for (String emailDestino : listaFrom) {
	    		
		    	String [] arrayEmail = emailDestino.split("@");
		    	dominio = arrayEmail[dominioEmail];
		    	isDominioSEFAZ = (dominio.equals(dominioSefaz)); 
	    		
		    	/*
		    	 * O email só poderá ser enviado para fora da sefaz se a 
		    	 *  aplicação estiver executando no ambiente produção.
		    	 */
	    		if (isAmbienteNaoProdutivo) {
	    			if (!isDominioSEFAZ) { 
	    				status = false;
						result.informarErro(emailDestino + ": dever ser do domínio "+ dominioSefaz );
	    			}
				}
	    	}
	    	
	    	String [] listacopia = email.getCopia().split(";");
	    	for (String emailDestino : listacopia) {

	    		String [] arrayEmail = emailDestino.split("@");
		    	dominio = arrayEmail[dominioEmail];
		    	isDominioSEFAZ = (dominio.equals(dominioSefaz)); 
	    		
		    	/*
		    	 * O email só poderá ser enviado para fora da sefaz se a 
		    	 *  aplicação estiver executando no ambiente produção.
		    	 */
	    		if (isAmbienteNaoProdutivo) {
	    			if (!isDominioSEFAZ) { 
	    				status = false;
						result.informarErro(emailDestino + ": dever ser do domínio "+ dominioSefaz );
	    			}
				}
	    	}	    	
	    	return status;	    	
	    }
	    
	    
}
