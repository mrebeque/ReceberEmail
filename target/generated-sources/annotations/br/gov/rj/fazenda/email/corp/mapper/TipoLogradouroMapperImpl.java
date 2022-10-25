package br.gov.rj.fazenda.email.corp.mapper;

import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroDTO;
import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroDTO.TipoLogradouroDTOBuilder;
import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroFormDTO;
import br.gov.rj.fazenda.email.corp.entity.TipoLogradouro;
import br.gov.rj.fazenda.email.corp.entity.TipoLogradouro.TipoLogradouroBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-25T12:23:52-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14 (Red Hat, Inc.)"
)
@Component
public class TipoLogradouroMapperImpl implements TipoLogradouroMapper {

    @Override
    public TipoLogradouroDTO toDTO(TipoLogradouro tipoLogradouro) {
        if ( tipoLogradouro == null ) {
            return null;
        }

        TipoLogradouroDTOBuilder tipoLogradouroDTO = TipoLogradouroDTO.builder();

        tipoLogradouroDTO.id( tipoLogradouro.getId() );
        tipoLogradouroDTO.abreviacao( tipoLogradouro.getAbreviacao() );
        tipoLogradouroDTO.descricao( tipoLogradouro.getDescricao() );

        return tipoLogradouroDTO.build();
    }

    @Override
    public TipoLogradouro toEntity(TipoLogradouroFormDTO tipoLogradouroFormDTO) {
        if ( tipoLogradouroFormDTO == null ) {
            return null;
        }

        TipoLogradouroBuilder tipoLogradouro = TipoLogradouro.builder();

        tipoLogradouro.id( tipoLogradouroFormDTO.getId() );
        tipoLogradouro.abreviacao( tipoLogradouroFormDTO.getAbreviacao() );
        tipoLogradouro.descricao( tipoLogradouroFormDTO.getDescricao() );

        return tipoLogradouro.build();
    }
}
