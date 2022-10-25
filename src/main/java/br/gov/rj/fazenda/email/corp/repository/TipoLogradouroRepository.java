package br.gov.rj.fazenda.email.corp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.gov.rj.fazenda.email.corp.entity.TipoLogradouro;


@Repository
public interface TipoLogradouroRepository extends JpaRepository<TipoLogradouro, Long>, JpaSpecificationExecutor<TipoLogradouro> {

}
