package br.gov.rj.sefaz.template.model.repository;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.gov.rj.fazenda.email.corp.entity.Fornecedor;
import br.gov.rj.fazenda.email.corp.repository.FornecedorRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FornecedorRepositoryTest {

    @Autowired
    FornecedorRepository repository;

    @Test
    void findAllTest() {
        val entity = repository.save(Fornecedor.builder().cnpj("Teste").nomeFantasia("Teste").build());
        val all = repository.findAll();

        assertTrue(all.size() > 0);
        assertTrue(all.stream().filter(f -> entity.getCnpj().equals(f.getCnpj())).count() > 0);
    }

}
