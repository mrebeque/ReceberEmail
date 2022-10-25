package br.gov.rj.sefaz.template.service;

import br.gov.rj.fazenda.email.corp.ApiApplication;
import br.gov.rj.fazenda.email.corp.mapper.ClienteMapper;
import br.gov.rj.fazenda.email.corp.repository.ClienteRepository;
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
class ClienteServiceTest {

	@Mock
	ClienteRepository repository;

	@Mock
	ClienteMapper mapper;

	@InjectMocks
	ClienteServiceTest service;

	@Test
	@DisplayName("Teste Unitário - ClienteService...")
	void teste() {
		val param1 = "1";
		val param2 = "1";
		assertEquals(param1, param2);
	}

}
