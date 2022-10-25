package br.gov.rj.fazenda.email.corp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.gov.rj.fazenda.email.corp.service.MensageriaService;

@Component
public class MessageListenerComponent implements ApplicationRunner {
	 
		@Autowired
		MensageriaService enviar;
		
	    @Override
	    public void run(ApplicationArguments args) throws Exception {
	    	HashMap<String, byte[]> anexos = new HashMap<>();
			Path path = Paths.get("C:\\tmp\\arquivos\\comprovante.pdf");
			
			byte[] arquivo =  Files.readAllBytes(path);		
			anexos.put("Comprovante-01.pdf", arquivo);
			anexos.put("Comprovante-02.pdf", arquivo);
			

	    	System.out.println("Eu vou testar!!");
	    	enviar.enviarMensagem("SBF", 
	    						  "mrepereira@fazenda.rj.gov.br", "marcelo.rebeque@gmail.com","",
	    						  "Teste-01", "Envio com anexos na pasta",
	    						  anexos
	    						  );
	       	System.out.println("Eu enviei!!");
	    }   	
}
