package br.gov.rj.fazenda.email.corp.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import br.gov.rj.fazenda.email.corp.dto.ClienteEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.ClienteSaidaDTO;
import br.gov.rj.fazenda.email.corp.entity.Cliente;

@Component
@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	ClienteSaidaDTO toDTO(Cliente cliente);
	Cliente toEntity(ClienteEntradaDTO clienteEntradaDTO);
}