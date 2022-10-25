package br.gov.rj.fazenda.email.corp.resource.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.rj.fazenda.email.corp.resource.FuncionalidadesResourceApi;


@RestController
@RequestMapping("/api/funcionalidades")
public class FuncionalidadesResource  implements FuncionalidadesResourceApi{
    /**
	 * {@code GET /api/funcionalidades/funcionalidade1 : obter dados da funcionalidade1 }
	 * 
	 * @return 
	 */
    @PreAuthorize("hasAuthority('PARAMETROS_CONSULTAR')")
    @GetMapping("/basica")
	public ResponseEntity<String> funcionalidadeBasica() {
		return  ResponseEntity
						.ok()
						.body("PARAMETROS_CONSULTAR");
	}
    
    /**
	 * {@code GET /api/diretoria/funcionalidade2 : obter dados da diretoria }
	 * 
	 * @return 
	 */
    @PreAuthorize("hasAuthority('RESTITUICAO_CONSULTAR')")
    @GetMapping("/diretoria")
	public ResponseEntity<String> diretoria() {
		return  ResponseEntity
						.ok()
						.body("RESTITUICAO_CONSULTAR");
	}
    
    /**
   	 * {@code GET /api/funcionalidades/funcionalidade1 : obter dados da funcionalidade1 }
   	 * 
   	 * @return 
   	 */
    @PreAuthorize("hasAuthority('PESQUISAR_PAGAMENTO')")
    @GetMapping("/funcionalidade1")
   	public ResponseEntity<String> funcionalidade1() {
   		return  ResponseEntity
   						.ok()
   						.body("PESQUISAR_PAGAMENTO");
    }
    
    /**
	 * {@code GET /api/funcionalidades/funcionalidade3 : obter dados da funcionalidade2 }
	 * 
	 * @return 
	 */
    @PreAuthorize("hasAuthority('TELA_INICIAL')")
    @GetMapping("/funcionalidade2")
	public ResponseEntity<String> funcionalidade2() {
		return  ResponseEntity
						.ok()
						.body("TELA_INICIAL");
	}
    
    /**
	 * {@code GET /api/funcionalidades/funcionalidade3 : obter dados da funcionalidade3 }
	 * 
	 * @return 
	 */
    @PreAuthorize("hasAuthority('MOEDAS_CONSULTAR')")
    @GetMapping("/funcionalidade3")
	public ResponseEntity<String> funcionalidade3() {
		return  ResponseEntity
						.ok()
						.body("MOEDAS_CONSULTAR");
	}

    @PreAuthorize("hasAuthority('TELA_INICIAL') or hasAuthority('PARAMETROS_CONSULTAR') or hasAuthority('MOEDAS_CONSULTAR')")
    @GetMapping("/qualquer")
	public String qualquer() {
		return "acesso através de qualquer funcionalidade 1, 2 ou 3";
	}

    @GetMapping("/publico")
	public String publico() {
		return "acesso a todas as funcionalidades publicas";
	}
}