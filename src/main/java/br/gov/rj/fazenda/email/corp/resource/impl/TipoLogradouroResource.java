package br.gov.rj.fazenda.email.corp.resource.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroDTO;
import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroFiltroDTO;
import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroFormDTO;
import br.gov.rj.fazenda.email.corp.exception.RecursoNaoEncontrado;
import br.gov.rj.fazenda.email.corp.resource.TipoLogradouroResourceApi;
import br.gov.rj.fazenda.email.corp.service.TipoLogradouroService;

/**
 * Resource Tipo Logradouro
 *
 */
@RestController
@RequestMapping("/api/tipo-logradouro")
public class TipoLogradouroResource implements TipoLogradouroResourceApi {

	@Autowired
	private TipoLogradouroService service;
	
	/**
	 * {@code GET /api/tipo-logradouro : obter todos os tipos logradouros }
	 * 
	 * @return
	 */

	@GetMapping
	public ResponseEntity<Page<TipoLogradouroDTO>> pesquisar(TipoLogradouroFiltroDTO filtro, @PageableDefault Pageable pageable) {
		return  ResponseEntity.ok().body(service.buscarPorParametros(filtro, pageable));
	}
	
	/**
	 * {@code GET /api/tipo-logradouro/{id} : Obter tipo logradouro por id }
	 * @param id
	 * @return
	 * @throws RecursoNaoEncontrado 
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TipoLogradouroDTO> load(@PathVariable Long id) throws RecursoNaoEncontrado {
		return ResponseEntity.ok().body(service.buscarPorId(id));
	}
	
	/**
	 * {@code POST /api/tipo-logradouro }
	 * @param tipoLogradouroFormDTO
	 * @return
	 * @throws URISyntaxException 
	 */
	@PostMapping
	public ResponseEntity<TipoLogradouroDTO> incluir(@RequestBody TipoLogradouroFormDTO tipoLogradouroFormDTO) throws URISyntaxException {
		TipoLogradouroDTO tipoLogradouroDTO = service.incluir(tipoLogradouroFormDTO);
		return ResponseEntity
				.created(new URI("/api/tipo-logradouro/" + tipoLogradouroDTO.getId()))
				.body(tipoLogradouroDTO);
	}
	
	/**
	 * {@code PUT /api/tipo-logradouro }
	 * @param tipoLogradouroFormDTO
	 * @return
	 * @throws RecursoNaoEncontrado 
	 */
	@PutMapping
	public ResponseEntity<TipoLogradouroDTO> atualizar(@RequestBody TipoLogradouroFormDTO tipoLogradouroFormDTO) throws RecursoNaoEncontrado {
		
		TipoLogradouroDTO tipoLogradouroDTO = service.atualizar(tipoLogradouroFormDTO);
		
		return ResponseEntity.ok().body(tipoLogradouroDTO);
	}
}
