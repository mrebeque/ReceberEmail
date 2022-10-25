package br.gov.rj.fazenda.email.corp.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.ObjectUtils;

import br.gov.rj.fazenda.email.corp.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString @EqualsAndHashCode
public class ClienteEntradaDTO {

	private Long id;
	private String nome;
	private String razaoSocial;
	private String cpf;
	private String cnpj;
	private String rg;
	private LocalDate dataNascimento;
	private String email;
	private String telefone;
	private String celular;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String numeroCNH;
	private String numeroRegistroCNH;
	private LocalDate validadeCNH;
	private String senha;
	private String observacao;
	
	public List<Predicate> addFiltros(Root<Cliente> entity, CriteriaQuery<?> query, CriteriaBuilder builder, Cliente filtro) {
		List<Predicate> predicates = new ArrayList<>();
	
		if(!ObjectUtils.isEmpty(filtro.getObservacao())) {
		predicates.add(builder.equal(entity.get("observacao"), filtro.getObservacao()));
		}
		
		return predicates;
	}
}
