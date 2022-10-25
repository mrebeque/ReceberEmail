package br.gov.rj.sefaz.template.model.repository;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.gov.rj.fazenda.email.corp.entity.Cliente;
import br.gov.rj.fazenda.email.corp.repository.ClienteRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClienteRepositoryTest {

    @Autowired
    ClienteRepository repository;

    @Test
    void findAllTest() {
        val entity = repository.save(Cliente.builder().observacao("Teste").build());
        val all = repository.findAll();

        assertTrue(all.size() > 0);
        assertTrue(all.stream().filter(f -> entity.getObservacao().equals(f.getObservacao())).count() > 0);
    }

}
