package br.gov.rj.fazenda.email.corp.service.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.rj.fazenda.email.corp.dto.FornecedorEntradaDTO;
import br.gov.rj.fazenda.email.corp.entity.Fornecedor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FornecedorSpecs {

	public static Specification<Fornecedor> comFiltro(FornecedorEntradaDTO filtro) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
