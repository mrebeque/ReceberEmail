package br.gov.rj.fazenda.email.corp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroDTO;
import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroFiltroDTO;
import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroFormDTO;
import br.gov.rj.fazenda.email.corp.entity.TipoLogradouro;
import br.gov.rj.fazenda.email.corp.exception.RecursoNaoEncontrado;
import br.gov.rj.fazenda.email.corp.mapper.TipoLogradouroMapper;
import br.gov.rj.fazenda.email.corp.repository.TipoLogradouroRepository;
import br.gov.rj.fazenda.email.corp.service.specs.TipoLogradouroSpecs;

/**
 * 
 */
@Service
public class TipoLogradouroService {

	@Autowired
	private TipoLogradouroRepository repository;

	@Autowired
	private TipoLogradouroMapper mapper;
	
	/**
	 * 
	 * @param filtro
	 * @param pageable
	 * @return
	 */
	public Page<TipoLogradouroDTO> buscarPorParametros(TipoLogradouroFiltroDTO filtro, Pageable pageable) {
		return repository.findAll(TipoLogradouroSpecs.comFiltro(filtro), pageable)
				.map(mapper::toDTO);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public TipoLogradouroDTO buscarPorId(Long id) throws RecursoNaoEncontrado {
		return repository.findById(id)
				.map(mapper::toDTO)
				.orElseThrow(() -> new RecursoNaoEncontrado());
	}
	
	/**
	 * 
	 * @param tipoLogradouroFormDTO
	 * @return
	 */
	public TipoLogradouroDTO incluir(TipoLogradouroFormDTO tipoLogradouroFormDTO) {
		
		TipoLogradouro tipoLogradouro = repository.save(mapper.toEntity(tipoLogradouroFormDTO));
		
		return mapper.toDTO(tipoLogradouro);
	}
	
	/**
	 * 
	 * @param tipoLogradouroFormDTO
	 * @return
	 * @throws RecursoNaoEncontrado 
	 */
	public TipoLogradouroDTO atualizar(TipoLogradouroFormDTO tipoLogradouroFormDTO) throws RecursoNaoEncontrado {

		Optional<TipoLogradouro> tipoLogradouro = repository.findById(tipoLogradouroFormDTO.getId());
		if(!tipoLogradouro.isPresent()) {
			throw new RecursoNaoEncontrado();
		}
		return mapper.toDTO(tipoLogradouro.get());
	}

}
