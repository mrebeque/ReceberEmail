package br.gov.rj.fazenda.email.corp.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import br.gov.rj.fazenda.email.corp.dto.FornecedorEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.FornecedorSaidaDTO;
import br.gov.rj.fazenda.email.corp.entity.Fornecedor;

@Component
@Mapper(componentModel = "spring")
public interface FornecedorMapper {
    FornecedorSaidaDTO toDTO(Fornecedor fornecedor);
    Fornecedor toEntity(FornecedorEntradaDTO entradaDTO);
}