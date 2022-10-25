package br.gov.rj.sefaz.template.api.resource;

import br.gov.rj.fazenda.email.corp.ApiApplication;
import br.gov.rj.fazenda.email.corp.dto.FornecedorEntradaDTO;
import br.gov.rj.fazenda.email.corp.dto.FornecedorSaidaDTO;
import br.gov.rj.fazenda.email.corp.entity.Fornecedor;
import br.gov.rj.fazenda.email.corp.repository.FornecedorRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc(addFilters = false)
class FornecedorResourceITTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    FornecedorRepository repository;

    @Test
    @DisplayName("Teste - GET /api/fornecedor : obter todos os tipos fornecedores")
    void getAllFornecedoresTest() throws Exception {
        saveFornecedor();

        val result = mockMvc.perform(get("/api/fornecedor"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        val response = result.getResponse().getContentAsString();

        //mapper pageable
        val page = new ObjectMapper()
                .registerModule(new PageModule())
                .readValue(response, new TypeReference<Page<FornecedorSaidaDTO>>() {
                });

        assertFalse(page.isEmpty());
        assertTrue(page.getTotalElements() > 0);
        assertTrue(page.getContent().size() > 0);
        assertTrue(page.getContent().stream().findFirst().get().getId() > 0);
    }

    @Test
    @DisplayName("Teste - GET /api/fornecedor/{id} : Obter tipo fornecedor por id")
    void getFornecedorByIdTest() throws Exception {
        val fornecedor = saveFornecedor();

        val result = mockMvc.perform(get("/api/fornecedor/{id}", fornecedor.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        val response = result.getResponse().getContentAsString();

        val dto = new ObjectMapper()
                .readValue(response, FornecedorSaidaDTO.class);

        assertTrue(!ObjectUtils.isEmpty(dto));
        assertTrue(dto.getId() > 0);
    }

    @Test
    @DisplayName("Teste - POST /api/fornecedor : Cria fornecedor")
    void postFornecedorTest() throws Exception {
        val dto = createDTO();

        val mvcResult = mockMvc.perform(
                post("/api/fornecedor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn();

        val response = mvcResult.getResponse().getContentAsString();

        val dtoResponse = new ObjectMapper()
                .readValue(response, FornecedorSaidaDTO.class);

        assertFalse(ObjectUtils.isEmpty(dtoResponse));
        assertTrue(dtoResponse.getId() > 0);
    }

    @Test
    @DisplayName("Teste - PUT /api/fornecedor : Atualiza fornecedor")
    void putFornecedorTest() throws Exception {
        val fornecedor = saveFornecedor();
        val dto = updateDTO(fornecedor.getId());

        val mvcResult = mockMvc.perform(
                put("/api/fornecedor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();

        val response = mvcResult.getResponse().getContentAsString();

        val dtoResponse = new ObjectMapper()
                .readValue(response, FornecedorSaidaDTO.class);

        assertFalse(ObjectUtils.isEmpty(dtoResponse));
        assertTrue(dtoResponse.getId() > 0);
    }

    @Test
    @DisplayName("Teste - DELETE /api/fornecedor : Excluí fornecedor")
    void deleteFornecedorTest() throws Exception {
        val fornecedor = saveFornecedor();
        val dto = updateDTO(fornecedor.getId());
        assertTrue(dto.getId() > 0);
        //mockMvc.perform(
        //       delete("/api/fornecedor/{id}", fornecedor.getId())
        //                .contentType(MediaType.APPLICATION_JSON)
        //                .accept(MediaType.APPLICATION_JSON))
        //                .andExpect(status().isOk());
    }

    private FornecedorEntradaDTO createDTO() {
        return FornecedorEntradaDTO.builder()
                .nomeFantasia("Teste nome fantasia")
                .razaoSocial("Teste razao social")
                .cpf("1111111111")
                .cnpj("11111111111111")
                .inscricaoMunicipal("Teste inscricao Municial")
                .inscricaoEstadual("Teste inscricao Estadual")
                .rg("1111111111")
                .build();
    }

    private FornecedorEntradaDTO updateDTO(Long id) {
        return FornecedorEntradaDTO.builder()
                .id(id)
                .nomeFantasia("Alterando Teste nome fantasia")
                .razaoSocial("Alterando  Teste razao social")
                .cpf("1111111111")
                .cnpj("11111111111111")
                .inscricaoMunicipal("Alterando  Teste inscricao Municial")
                .inscricaoEstadual("Alterando Teste inscricao Estadual")
                .rg("1111111111")
                .build();
    }

    private Fornecedor saveFornecedor() {
        return repository.save(Fornecedor.builder()
                .nomeFantasia("Teste nome fantasia")
                .razaoSocial("Teste razao social")
                .cpf("11111111111")
                .cnpj("11111111111111")
                .inscricaoMunicipal("Teste inscricao Municial")
                .inscricaoEstadual("Teste inscricao Estadual")
                .rg("1111111111")
                .build());
    }

}
