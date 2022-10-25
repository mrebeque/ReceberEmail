package br.gov.rj.fazenda.email.corp.resource.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.rj.fazenda.email.corp.dto.ClienteEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.ClienteSaidaDTO;
import br.gov.rj.fazenda.email.corp.dto.FornecedorEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.FornecedorSaidaDTO;
import br.gov.rj.fazenda.email.corp.exception.RecursoNaoEncontrado;
import br.gov.rj.fazenda.email.corp.resource.ClienteResourceApi;
import br.gov.rj.fazenda.email.corp.service.ClienteService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/cliente")
public class ClienteResource implements ClienteResourceApi {
	
	@Autowired
	private ClienteService service;
	
	/**
	 * {@code GET /api/cliente : obter todos os clientes }
	 * 
	 * @return 
	 */
	@GetMapping
	public ResponseEntity<Page<ClienteSaidaDTO>> pesquisar(ClienteEntradaDTO entrada, @PageableDefault Pageable pageable) {
		return  ResponseEntity
						.ok()
						.body(service.buscarPorParametros(entrada, pageable));
	}
	
	/**
	 * {@code GET /api/cliente/{id} : Obtém cliente por id }
	 * @param id
	 * @return {@link ClienteSaidaDTO}
	 * @throws RecursoNaoEncontrado 
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ClienteSaidaDTO> load(@PathVariable Long id) throws RecursoNaoEncontrado {
		return ResponseEntity
				.ok()
				.body(service.buscarPorId(id));
	}
	
	/**
	 * {@code POST /api/cliente }
	 * @param entradaDTO {@link ClienteEntradaDTO}
	 * @return {@link ClienteSaidaDTO}
	 * @throws URISyntaxException 
	 */
	@PostMapping
	public ResponseEntity<ClienteSaidaDTO> incluir(@RequestBody ClienteEntradaDTO entradaDTO) throws URISyntaxException {
		ClienteSaidaDTO saidaDTO = service.incluir(entradaDTO);
		log.info("Incluido cliente: " + saidaDTO);
		return ResponseEntity
				.created(new URI("/api/fornecedor/" + saidaDTO.getId()))
				.body(saidaDTO);
	}
	
	/**
	 * {@code PUT /api/cliente }
	 * @param entradaDTO {@link FornecedorEntradaDTO}
	 * @return {@link FornecedorSaidaDTO}
	 * @throws RecursoNaoEncontrado 
	 */
	@PutMapping
	public ResponseEntity<ClienteSaidaDTO> atualizar(@RequestBody ClienteEntradaDTO entradaDTO) throws RecursoNaoEncontrado {
		ClienteSaidaDTO saidaDTO = service.atualizar(entradaDTO);
		return ResponseEntity.ok().body(saidaDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) throws RecursoNaoEncontrado {
		service.excluir(id);
		return ResponseEntity.ok().build();
	}

}
