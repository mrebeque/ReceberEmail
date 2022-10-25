package br.gov.rj.fazenda.email.corp.service.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.rj.fazenda.email.corp.dto.ClienteEntradaDTO;
import br.gov.rj.fazenda.email.corp.entity.Cliente;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteSpecs {

	public static Specification<Cliente> comFiltro(ClienteEntradaDTO filtro) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
