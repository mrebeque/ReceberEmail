package br.gov.rj.fazenda.email.corp.mapper;

import br.gov.rj.fazenda.email.corp.dto.FornecedorEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.FornecedorSaidaDTO;
import br.gov.rj.fazenda.email.corp.dto.FornecedorSaidaDTO.FornecedorSaidaDTOBuilder;
import br.gov.rj.fazenda.email.corp.entity.Fornecedor;
import br.gov.rj.fazenda.email.corp.entity.Fornecedor.FornecedorBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-25T12:23:52-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14 (Red Hat, Inc.)"
)
@Component
public class FornecedorMapperImpl implements FornecedorMapper {

    @Override
    public FornecedorSaidaDTO toDTO(Fornecedor fornecedor) {
        if ( fornecedor == null ) {
            return null;
        }

        FornecedorSaidaDTOBuilder fornecedorSaidaDTO = FornecedorSaidaDTO.builder();

        fornecedorSaidaDTO.id( fornecedor.getId() );
        fornecedorSaidaDTO.nomeFantasia( fornecedor.getNomeFantasia() );
        fornecedorSaidaDTO.razaoSocial( fornecedor.getRazaoSocial() );
        fornecedorSaidaDTO.cpf( fornecedor.getCpf() );
        fornecedorSaidaDTO.cnpj( fornecedor.getCnpj() );
        fornecedorSaidaDTO.inscricaoMunicipal( fornecedor.getInscricaoMunicipal() );
        fornecedorSaidaDTO.inscricaoEstadual( fornecedor.getInscricaoEstadual() );
        fornecedorSaidaDTO.rg( fornecedor.getRg() );

        return fornecedorSaidaDTO.build();
    }

    @Override
    public Fornecedor toEntity(FornecedorEntradaDTO entradaDTO) {
        if ( entradaDTO == null ) {
            return null;
        }

        FornecedorBuilder fornecedor = Fornecedor.builder();

        fornecedor.id( entradaDTO.getId() );
        fornecedor.nomeFantasia( entradaDTO.getNomeFantasia() );
        fornecedor.razaoSocial( entradaDTO.getRazaoSocial() );
        fornecedor.cpf( entradaDTO.getCpf() );
        fornecedor.cnpj( entradaDTO.getCnpj() );
        fornecedor.inscricaoMunicipal( entradaDTO.getInscricaoMunicipal() );
        fornecedor.inscricaoEstadual( entradaDTO.getInscricaoEstadual() );
        fornecedor.rg( entradaDTO.getRg() );
        fornecedor.email( entradaDTO.getEmail() );
        fornecedor.telefone( entradaDTO.getTelefone() );
        fornecedor.celular( entradaDTO.getCelular() );
        fornecedor.rua( entradaDTO.getRua() );
        fornecedor.numero( entradaDTO.getNumero() );
        fornecedor.complemento( entradaDTO.getComplemento() );
        fornecedor.bairro( entradaDTO.getBairro() );
        fornecedor.cep( entradaDTO.getCep() );

        return fornecedor.build();
    }
}
