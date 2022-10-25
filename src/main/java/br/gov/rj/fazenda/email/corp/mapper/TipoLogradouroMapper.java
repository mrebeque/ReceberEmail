package br.gov.rj.fazenda.email.corp.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroDTO;
import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroFormDTO;
import br.gov.rj.fazenda.email.corp.entity.TipoLogradouro;

@Component
@Mapper(componentModel = "spring")
public interface TipoLogradouroMapper {
    TipoLogradouroDTO toDTO(TipoLogradouro tipoLogradouro);
    TipoLogradouro toEntity(TipoLogradouroFormDTO tipoLogradouroFormDTO);
}