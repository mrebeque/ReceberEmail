package br.gov.rj.fazenda.email.corp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.gov.rj.fazenda.email.corp.dto.ClienteEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.ClienteSaidaDTO;
import br.gov.rj.fazenda.email.corp.entity.Cliente;
import br.gov.rj.fazenda.email.corp.exception.RecursoNaoEncontrado;
import br.gov.rj.fazenda.email.corp.mapper.ClienteMapper;
import br.gov.rj.fazenda.email.corp.repository.ClienteRepository;
import br.gov.rj.fazenda.email.corp.service.specs.ClienteSpecs;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private ClienteMapper mapper;
	
	/**
	 * 
	 * @param entrada
	 * @param pageable
	 * @return
	 */
	public Page<ClienteSaidaDTO> buscarPorParametros(ClienteEntradaDTO entrada, Pageable pageable) {
		return repository.findAll(ClienteSpecs.comFiltro(entrada), pageable).map(mapper::toDTO);
	}
	
	/**
	 * 
	 * @param id
	 * @return {@link ClienteSaidaDTO}
	 * @throws RecursoNaoEncontrado 
	 */
	public ClienteSaidaDTO buscarPorId(Long id) throws RecursoNaoEncontrado {
		return repository.findById(id).map(mapper::toDTO)
				.orElseThrow(() -> new RecursoNaoEncontrado());
	}
	
	/**
	 * 
	 * @param entradaDTO {@link ClienteEntradaDTO}
	 * @return
	 */
	public ClienteSaidaDTO incluir(ClienteEntradaDTO entradaDTO) {
		Cliente entity = repository.save(mapper.toEntity(entradaDTO));
		return mapper.toDTO(entity);
	}
	
	/**
	 * 
	 * @param entradaDTO {@link ClienteEntradaDTO}
	 * @return {@link ClienteSaidaDTO}
	 * @throws RecursoNaoEncontrado 
	 */
	public ClienteSaidaDTO atualizar(ClienteEntradaDTO entradaDTO) throws RecursoNaoEncontrado {
		boolean existsEntity = repository.existsById(entradaDTO.getId());
		if(!existsEntity) throw new RecursoNaoEncontrado();
		Cliente entity = mapper.toEntity(entradaDTO);
		return mapper.toDTO(repository.save(entity));
	}
	
	/**
	 * 
	 */
	public void excluir(Long id) {
		repository.deleteById(id);
	}

	/**
	 * 
	 * @param entradaDTO {@link ClienteEntradaDTO}
	 */
	public void excluir(ClienteEntradaDTO entradaDTO) {
		repository.deleteById(entradaDTO.getId());
	}
}
