package br.gov.rj.fazenda.email.corp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.gov.rj.fazenda.email.corp.dto.FornecedorEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.FornecedorSaidaDTO;
import br.gov.rj.fazenda.email.corp.entity.Fornecedor;
import br.gov.rj.fazenda.email.corp.exception.RecursoNaoEncontrado;
import br.gov.rj.fazenda.email.corp.mapper.FornecedorMapper;
import br.gov.rj.fazenda.email.corp.repository.FornecedorRepository;
import br.gov.rj.fazenda.email.corp.service.specs.FornecedorSpecs;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repository;

	@Autowired
	private FornecedorMapper mapper;
	
	/**
	 * 
	 * @param entrada
	 * @param pageable
	 * @return
	 */
	public Page<FornecedorSaidaDTO> buscarPorParametros(FornecedorEntradaDTO entrada, Pageable pageable) {
		return repository.findAll(FornecedorSpecs.comFiltro(entrada), pageable).map(mapper::toDTO);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws RecursoNaoEncontrado 
	 */
	public FornecedorSaidaDTO buscarPorId(Long id) throws RecursoNaoEncontrado {
		return repository.findById(id).map(mapper::toDTO)
		.orElseThrow(() -> new RecursoNaoEncontrado());

	}
	
	/**
	 * 
	 * @param fornecedor
	 * @return
	 */
	public FornecedorSaidaDTO incluir(FornecedorEntradaDTO entradaDTO) {
		Fornecedor fornecedor = repository.save(mapper.toEntity(entradaDTO));
		return mapper.toDTO(fornecedor);
	}
	
	/**
	 * 
	 * @param entradaDTO
	 * @return
	 * @throws RecursoNaoEncontrado 
	 */
	public FornecedorSaidaDTO atualizar(FornecedorEntradaDTO entradaDTO) throws RecursoNaoEncontrado {
		Optional<Fornecedor> fornecedor = repository.findById(entradaDTO.getId());
		if(!fornecedor.isPresent()) throw new RecursoNaoEncontrado();
		Fornecedor entity = mapper.toEntity(entradaDTO);
		return mapper.toDTO(repository.save(entity));
	}
	
	/**
	 * 
	 * @param entradaDTO
	 */
	public void excluir(Long id) {
		repository.deleteById(id);
	}

	/**
	 * 
	 * @param entradaDTO
	 */
	public void excluir(FornecedorEntradaDTO entradaDTO) {
		repository.deleteById(entradaDTO.getId());
	}
}
