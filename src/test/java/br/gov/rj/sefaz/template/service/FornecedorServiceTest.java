package br.gov.rj.sefaz.template.service;

import br.gov.rj.fazenda.email.corp.ApiApplication;
import br.gov.rj.fazenda.email.corp.mapper.FornecedorMapper;
import br.gov.rj.fazenda.email.corp.repository.FornecedorRepository;
import br.gov.rj.fazenda.email.corp.service.FornecedorService;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes = ApiApplication.class)
class FornecedorServiceTest {

    @Mock
    FornecedorRepository repository;

    @Mock
    FornecedorMapper mapper;

    @InjectMocks
    FornecedorService service;

    @Test
    @DisplayName("Teste Unitário - FornecedorService...")
    void teste() {
        val param1 = "1";
        val param2 = "1";
        assertEquals(param1, param2);
    }

}
