
package br.gov.rj.sefaz.template.api.resource;

import br.gov.rj.fazenda.email.corp.ApiApplication;
import br.gov.rj.fazenda.email.corp.dto.ClienteEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.ClienteSaidaDTO;
import br.gov.rj.fazenda.email.corp.entity.Cliente;
import br.gov.rj.fazenda.email.corp.repository.ClienteRepository;
import br.gov.rj.sefaz.template.config.PageModule;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ObjectUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc(addFilters = false)
class ClienteResourceITTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ClienteRepository repository;

	@Test
	@DisplayName("Teste - GET /api/cliente : obter todos os clientes")
	void getAllClientesTest() throws Exception {
		saveCliente();

		val result = mockMvc.perform(get("/api/cliente"))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();

		val response = result.getResponse().getContentAsString();

		//mapper pageable
		val page = new ObjectMapper()
				.registerModule(new PageModule())
				.readValue(response, new TypeReference<Page<ClienteSaidaDTO>>() {
				});

		assertFalse(page.isEmpty());
		assertTrue(page.getTotalElements() > 0);
		assertTrue(page.getContent().size() > 0);
		assertTrue(page.getContent().stream().findFirst().get().getId() > 0);
	}

	@Test
	@DisplayName("Teste - GET /api/cliente/{id} : Obtém cliente por id")
	void getClienteByIdTest() throws Exception {
		val cliente = saveCliente();

		val result = mockMvc.perform(get("/api/cliente/{id}", cliente.getId()))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();

		val response = result.getResponse().getContentAsString();

		val dto = new ObjectMapper()
				.readValue(response, ClienteSaidaDTO.class);

		assertFalse(ObjectUtils.isEmpty(dto));
		assertTrue(dto.getId() > 0);
	}

	@Test
	@DisplayName("Teste - POST /api/cliente : Cria cliente")
	void postClienteTest() throws Exception {
		val dto = createDTO();

		val mvcResult = mockMvc.perform(
				post("/api/cliente")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andReturn();

		val response = mvcResult.getResponse().getContentAsString();

		val dtoResponse = new ObjectMapper()
				.readValue(response, ClienteSaidaDTO.class);

		assertFalse(ObjectUtils.isEmpty(dtoResponse));
		assertTrue(dtoResponse.getId() > 0);
	}

	@Test
	@DisplayName("Teste - PUT /api/cliente : Atualiza cliente")
	void putClienteTest() throws Exception {
		val cliente = saveCliente();
		val dto = updateDTO(cliente.getId());

		val mvcResult = mockMvc.perform(
				put("/api/cliente")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andReturn();

		val response = mvcResult.getResponse().getContentAsString();

		val dtoResponse = new ObjectMapper()
				.readValue(response, ClienteSaidaDTO.class);

		assertFalse(ObjectUtils.isEmpty(dtoResponse));
		assertTrue(dtoResponse.getId() > 0);
	}

	@Test
	@DisplayName("Teste - DELETE /api/cliente : Exclui cliente")
	void deleteClienteTest() throws Exception {
		val cliente = saveCliente();
		//val dto = updateDTO(cliente.getId());

		mockMvc.perform(
			delete("/api/cliente/{id}", cliente.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private ClienteEntradaDTO createDTO() {
		return ClienteEntradaDTO.builder()
				.observacao("Teste observação")
				.senha("aaaa")
				.build();
	}

	private Cliente saveCliente() {
		return repository.save(Cliente.builder()
				.observacao("Teste Observção")
				.build());
	}

	private ClienteEntradaDTO updateDTO(Long id) {
		return ClienteEntradaDTO.builder()
				.id(id)
				.observacao("Teste observação")
				.senha("aaaa")
				.build();
	}
}

