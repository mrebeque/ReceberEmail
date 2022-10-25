package br.gov.rj.fazenda.email.corp.service.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.rj.fazenda.email.corp.dto.TipoLogradouroFiltroDTO;
import br.gov.rj.fazenda.email.corp.entity.TipoLogradouro;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 * 
 * @author cpaiva
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TipoLogradouroSpecs {
	
	public static Specification<TipoLogradouro> comFiltro(TipoLogradouroFiltroDTO filtro) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
