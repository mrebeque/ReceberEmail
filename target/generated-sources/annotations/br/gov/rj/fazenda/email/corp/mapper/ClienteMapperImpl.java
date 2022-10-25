package br.gov.rj.fazenda.email.corp.mapper;

import br.gov.rj.fazenda.email.corp.dto.ClienteEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.ClienteSaidaDTO;
import br.gov.rj.fazenda.email.corp.dto.ClienteSaidaDTO.ClienteSaidaDTOBuilder;
import br.gov.rj.fazenda.email.corp.entity.Cliente;
import br.gov.rj.fazenda.email.corp.entity.Cliente.ClienteBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-25T12:23:52-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14 (Red Hat, Inc.)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteSaidaDTO toDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteSaidaDTOBuilder clienteSaidaDTO = ClienteSaidaDTO.builder();

        clienteSaidaDTO.id( cliente.getId() );
        clienteSaidaDTO.nome( cliente.getNome() );
        clienteSaidaDTO.razaoSocial( cliente.getRazaoSocial() );
        clienteSaidaDTO.cpf( cliente.getCpf() );
        clienteSaidaDTO.cnpj( cliente.getCnpj() );
        clienteSaidaDTO.rg( cliente.getRg() );
        clienteSaidaDTO.dataNascimento( cliente.getDataNascimento() );
        clienteSaidaDTO.email( cliente.getEmail() );
        clienteSaidaDTO.telefone( cliente.getTelefone() );
        clienteSaidaDTO.celular( cliente.getCelular() );
        clienteSaidaDTO.rua( cliente.getRua() );
        clienteSaidaDTO.numero( cliente.getNumero() );
        clienteSaidaDTO.complemento( cliente.getComplemento() );
        clienteSaidaDTO.bairro( cliente.getBairro() );
        clienteSaidaDTO.cep( cliente.getCep() );
        clienteSaidaDTO.numeroCNH( cliente.getNumeroCNH() );
        clienteSaidaDTO.numeroRegistroCNH( cliente.getNumeroRegistroCNH() );
        clienteSaidaDTO.validadeCNH( cliente.getValidadeCNH() );
        clienteSaidaDTO.senha( cliente.getSenha() );
        clienteSaidaDTO.observacao( cliente.getObservacao() );

        return clienteSaidaDTO.build();
    }

    @Override
    public Cliente toEntity(ClienteEntradaDTO clienteEntradaDTO) {
        if ( clienteEntradaDTO == null ) {
            return null;
        }

        ClienteBuilder cliente = Cliente.builder();

        cliente.id( clienteEntradaDTO.getId() );
        cliente.nome( clienteEntradaDTO.getNome() );
        cliente.razaoSocial( clienteEntradaDTO.getRazaoSocial() );
        cliente.cpf( clienteEntradaDTO.getCpf() );
        cliente.cnpj( clienteEntradaDTO.getCnpj() );
        cliente.rg( clienteEntradaDTO.getRg() );
        cliente.dataNascimento( clienteEntradaDTO.getDataNascimento() );
        cliente.email( clienteEntradaDTO.getEmail() );
        cliente.telefone( clienteEntradaDTO.getTelefone() );
        cliente.celular( clienteEntradaDTO.getCelular() );
        cliente.rua( clienteEntradaDTO.getRua() );
        cliente.numero( clienteEntradaDTO.getNumero() );
        cliente.complemento( clienteEntradaDTO.getComplemento() );
        cliente.bairro( clienteEntradaDTO.getBairro() );
        cliente.cep( clienteEntradaDTO.getCep() );
        cliente.numeroCNH( clienteEntradaDTO.getNumeroCNH() );
        cliente.numeroRegistroCNH( clienteEntradaDTO.getNumeroRegistroCNH() );
        cliente.validadeCNH( clienteEntradaDTO.getValidadeCNH() );
        cliente.senha( clienteEntradaDTO.getSenha() );
        cliente.observacao( clienteEntradaDTO.getObservacao() );

        return cliente.build();
    }
}
