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

import br.gov.rj.fazenda.email.corp.dto.FornecedorEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.FornecedorSaidaDTO;
import br.gov.rj.fazenda.email.corp.exception.RecursoNaoEncontrado;
import br.gov.rj.fazenda.email.corp.resource.FornecedorResourceApi;
import br.gov.rj.fazenda.email.corp.service.FornecedorService;

/**
 * Resource Tipo Logradouro
 *
 */
@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorResource implements FornecedorResourceApi {

	@Autowired
	private FornecedorService service;
	
	/**
	 * {@code GET /api/fornecedor : obter todos os tipos fornecedores }
	 * 
	 * @return 
	 */
	@GetMapping
	public ResponseEntity<Page<FornecedorSaidaDTO>> pesquisar(FornecedorEntradaDTO entrada, @PageableDefault Pageable pageable) {
		return  ResponseEntity
						.ok()
						.body(service.buscarPorParametros(entrada, pageable));
	}
	
	/**
	 * {@code GET /api/fornecedor/{id} : Obter tipo fornecedor por id }
	 * @param id
	 * @return {@link FornecedorSaidaDTO}
	 * @throws RecursoNaoEncontrado 
	 */
	@GetMapping("/{id}")
	public ResponseEntity<FornecedorSaidaDTO> load(@PathVariable Long id) throws RecursoNaoEncontrado {
		return ResponseEntity
				.ok()
				.body(service.buscarPorId(id));
	}
	
	/**
	 * {@code POST /api/fornecedor }
	 * @param entradaDTO {@link IncluirFornecedorDTO}
	 * @return {@link FornecedorSaidaDTO}
	 * @throws URISyntaxException 
	 */
	@PostMapping
	public ResponseEntity<FornecedorSaidaDTO> incluir(@RequestBody FornecedorEntradaDTO entradaDTO) throws URISyntaxException {
		FornecedorSaidaDTO saidaDTO = service.incluir(entradaDTO);
		return ResponseEntity
				.created(new URI("/api/fornecedor/" + saidaDTO.getId()))
				.body(saidaDTO);
	}
	
	/**
	 * {@code PUT /api/fornecedor }
	 * @param entradaDTO {@link FornecedorEntradaDTO}
	 * @return {@link FornecedorSaidaDTO}
	 * @throws RecursoNaoEncontrado 
	 */
	@PutMapping
	public ResponseEntity<FornecedorSaidaDTO> atualizar(@RequestBody FornecedorEntradaDTO entradaDTO) throws RecursoNaoEncontrado {
		FornecedorSaidaDTO saidaDTO = service.atualizar(entradaDTO);
		return ResponseEntity.ok().body(saidaDTO);
	}
	
	/**
	 * {@code DELETE /api/fornecedor }
	 * @param entradaDTO {@link FornecedorEntradaDTO}
	 * @return {@link FornecedorSaidaDTO}
	 * @throws RecursoNaoEncontrado 
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) throws RecursoNaoEncontrado {
		service.excluir(id);
		return ResponseEntity.ok().build();
	}

}
