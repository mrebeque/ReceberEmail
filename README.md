<h1> Enviar Email Corporativo </h1>



<h2> Requisitos </h2>

- Spring Tool Suite 4+ (recomendável)
- Java 11
- JDK 11
- JBoss 7+
- Maven



<h2> Configuração de ambiente - Template Service </h2>

<h3> 1º Passo - Importar Template </h3>

- Faça clone do projeto https://gitlab.fazenda.rj.gov.br/sefaz/subtic/susist/arquitetura/framework/transicao/aplicacoes/template-service
- Abra a IDE e importe o Projeto: **Import > Maven > Existing Maven Projects > Next > Browser...**
- Procure pelo projeto
- Escolha a pasta raiz do projeto
- Clique em **Ok** e **Finish**



<h3> 2º Passo - Configurar JDK </h3>

- Com o botão direito do mouse clique sobre o diretório template-angular-web
- Em **Properties > Java Build Path > Libraries** selecione **JRE System Library** e clique em **Edit...**
- Selecione **Alternate JRE** e clique em **Installed JREs... > Add... > Standard VM > Next > Directory...**
- Procure pelo diretório do JDK instalado em sua máquina **JDK 11 > Finish**
- Selecione a caixa de seleção **jdk 11 > Apply > Ok**



<h3> 3º Passo - Configurar Maven </h3>

- Clique com o botão direito do mouse sobre o diretório template-angular-web
- Em **Run as... > Maven Build...**
- Em **Goals** digite **clean install**
- Selecione **Update Snapshots** e **Skip Tests**
- Em **Maven Runtime** clique **Configure... > Add...**
- Em **Installation home** clique em **Directory...**
- Procure pelo diretório do Maven instalado em sua máquina **Maven > Finish**
- Selecione a caixa de seleção **apache maven > Apply > Ok > Apply > Run**


<h4>Nota: O arquivo settings.xml deve está configurado</h4>


<h3>:: 4º Passo - Configurar JBoss ::</h3>

- No menu clique em **Window > Show view > Other > Server > Servers > Open**
- Clique em **New > Server**
- Clique no link **No servers are available. Click this link to create a new server**
- Em **Red Hat JBoss Middleware > Red Hat JBoss Entrerprise Application Platform 7.0 > Next > Next > Browser...**

- Obs.: Se não houver, clique no item para baixar o Red Hat JBoss Middleware

- Escolha **Create new runtime (next page)**
- Em **Home Directory** clique em **Browser...** e procure pelo diretório do JBoss instalado em sua máquina
- Selecione **Alternate JRE** escolha **JDK 11 > Browse...**
- Obs.: Se não houver, clique em **Installed JREs... > Add... > Standard VM > Directory...** procure pelo JDK 11 e selecione a caixa de seleção no item adicionado

- Em **Configuration file** clique em **Browser...** e procure pelo standalone no diretório do servidor JBoss em **standalone > Configuration > Abrir > Next**

- Selecione o pacote template-angular-ear clique em **Add** e **Finish**
- Clique na raiz do servidor JBoss adicionado, com o botão direito do mouse em **Clean...** e pressione **Alt + F5** para atualizar os pacotes
- Clique em **Start** ou **Debug** para executar o servidor de aplicação

# Lombok

### Contexto
* Entidades, VOs, DTOs e outros objetos de dados fazem uso da biblioteca lombok para tornar o código menos verboso, substituindo as implementações de getters, setters, hashcode ,equals por anotações do lombok. 

### Configuração

* Baixar lib lombok ou Ir na pasta ~/.m2/repository/org/project/lombok/lombok/<diretorio-versao-mais-atual>/
* Executar o jar do lombok
* Irá aparecer o instalador do lombok, então selecionar o local onde o eclipse foi instalado.
* Clique em Install/Update e feche o instalador.
* Reiniciar o eclipse

# Documentação Swagger

### Contexto
* Todas as APIs são documentadas através de uma interface Swagger da biblioteca Open API, onde é possível realizar requests para a mesma, via browser.
  * URL de acesso: http://localhost:8080/template-service/swagger-ui.html
* Para utilizar o swagger web deve-se informar o token de autenticação.
* Uma boa prática para documentar a API é criar uma interface para implementar no controller, toda documentação fica na interface.
  * Exemplo:
```
@Tag(name = "Tipo Logradouro")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
        @ApiResponse(responseCode = "400", description = "Bad Request: Parâmetro informado é inválido."),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado."),
        @ApiResponse(responseCode = "403", description = "Usuário sem permissão."),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor."),
        @ApiResponse(responseCode = "503", description = "Erro comunicação gatway."),
        @ApiResponse(responseCode = "504", description = "Serviço inexistente.")
})
public interface TipoLogradouroResourceApi {

    @Operation(summary = "Obtém todos os tipos logradouros")
    ResponseEntity<Page<TipoLogradouroDTO>> pesquisar(TipoLogradouroFiltroDTO filtro, @PageableDefault Pageable pageable);

    @Operation(summary = "Obtém tipo logradouro por id")
    ResponseEntity<TipoLogradouroDTO> load(@PathVariable Long id) throws RecursoNaoEncontrado;

    @Operation(summary = "Cria um tipo logradouro")
    ResponseEntity<TipoLogradouroDTO> incluir(@RequestBody TipoLogradouroFormDTO tipoLogradouroFormDTO) throws URISyntaxException;

    @Operation(summary = "Altera um tipo logradouro")
    ResponseEntity<TipoLogradouroDTO> atualizar(@RequestBody TipoLogradouroFormDTO tipoLogradouroFormDTO) throws RecursoNaoEncontrado;

}
```

### Configuração
* Importar a dependência.
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.5.10</version>
</dependency>
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-data-rest</artifactId>
    <version>1.5.2</version>
</dependency>
```
* Configurar o application.yml para cada ambiente, os atributos abaixo são tags que estão declaradas no pom.xml.
```
name: "@name@"
description: "@description@"
version: "@version@"
```
* Criar a classe de configuração para o swagger mapear o projeto.
```
@Configuration
public class OpenApiConfig {

	@Value("${name}")
	public String name;

	@Value("${description}")
	public String description;

	@Value("${version}")
	public String version;

	@Bean
	public OpenAPI api() {
		return new OpenAPI()
				.info(new Info()
						.title(name)
						.version(version)
						.description(description)
						.termsOfService("http://swagger.io/terms")
						.license(new License().name("Apache 2.0")));
	}

}
```

### Referência
[https://springdoc.org/](https://springdoc.org/)

# Bean Mapper

### Contexto
* Como boa prática não utilizasse o mapeamento de de dados de objetos para outros objetos manualemnte, para isso utilizamos uma biblioteca chamada **Orika**.
* **Orika** é uma estrutura de mapeamento Java Bean que copia recursivamente dados de um objeto para outro.
* **Orika** usa geração de byte code para criar mapeadores rápidos com sobrecarga mínima, tornando-o muito mais rápido do que outros mapeadores.
* Para que o mapeamento funcione corretamente sem utilizar os métodos setters de uma classe(padrão de imutabilidade), a classe que vai receber os dados deve conter os mesmos atributos(com a nomenclatura igual).
  * Exemplo:
```
@Component
@RequiredArgsConstructor
public class TipoLogradouroAssembler {

	@Autowired
	private MapperFactory mapperFactory;
	
	public TipoLogradouroDTO toDTO(TipoLogradouro tipoLogradouro) {
		return mapperFactory
				.getMapperFacade(TipoLogradouro.class, TipoLogradouroDTO.class)
				.map(tipoLogradouro);
	}
	
	public TipoLogradouro toEntity(TipoLogradouroFormDTO tipoLogradouroFormDTO) {
		return mapperFactory.getMapperFacade(TipoLogradouroFormDTO.class, TipoLogradouro.class).map(tipoLogradouroFormDTO);
	}
}
```

```
@Entity
@Table(name = "TIPO_LOGRADOURO")
@NamedQuery(name = "TipoLogradouro.findAll", query = "SELECT t FROM TipoLogradouro t")
public class TipoLogradouro {

	@Id
	@Column(name = "CO_TP_LOGRADOURO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NO_ABREV_TP_LOGRADOURO")
	private String abreviacao;

	@Column(name = "NO_TP_LOGRADOURO")
	private String descricao; // DESCRICAO

}
```

```
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString @EqualsAndHashCode
public class TipoLogradouroDTO {
	
	private Long id;
	private String abreviacao;
	private String descricao;

}
```

### Configuração
* Importar a dependência.
```
<dependency>
    <groupId>ma.glasnost.orika</groupId>
    <artifactId>orika-core</artifactId>
    <version>1.4.6</version>
</dependency>
```
* Criar a classe de configuração que injeta um bean que instancia o Orika Mapper, para possibilitar a injeção de dependencia do Orika nas classes de mapeamento.
```
@Configuration
public class OrikaMapperConfig {
	
	@Bean
	public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder().build();
	}
}
```

### Referência
* [Orika Mapping](https://www.baeldung.com/orika-mapping)

# Testes - JUnit

### Contexto
* Testes de integração são requests http realizados para os endpoints, executando assim o fluxo todo do sistema, como se fosse um request real para a aplicação.
* Uma boa prática, antes de executar uma requisição para um endpoint é gerar a informação na base de dados para garantir o teste com sucesso, em caso de um teste de consulta.
* Os teste de integração geram dados apenas em tempo de execução, quando finalizada a transação, por default é feito automaticamente o rollback dos dados em base, é possível configurar para que os dados sejam persistidos de fato.
* Testes unitários são testes pontuais em determinadas regras de negócio, este teste é realizado através de mock.
* Observação: apesar de o exemplo utilizar o componente mock, nenhum dado é mockado, o request http é realizado.
* Exemplo Teste de integração:
```
@ActiveProfiles("test")
@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc(addFilters = false)
class TipoLogradouroResourceITTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TipoLogradouroRepository repository;

	@Test
	@DisplayName("Teste - GET /api/tipo-logradouro : obtem todos os tipos logradouros")
	void getAllTipoLogradouroTest() throws Exception {
		saveTipoLogradouro();

		val result = mockMvc.perform(get("/api/tipo-logradouro"))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();

		val response = result.getResponse().getContentAsString();

		//mapper pageable
		val page = new ObjectMapper()
				.registerModule(new PageModule())
				.readValue(response, new TypeReference<Page<TipoLogradouroDTO>>() {});

		assertFalse(page.isEmpty());
		assertTrue(page.getTotalElements() > 0);
		assertTrue(page.getContent().size() > 0);
		assertTrue(page.getContent().stream().findFirst().get().getId() > 0);
	}
}
```
* Exemplo Teste unitário:
```
@ActiveProfiles("test")
@SpringBootTest(classes = ApiApplication.class)
class TipoLogradouroServiceTest {

    @Mock
    TipoLogradouroRepository repository;

    @Mock
    TipoLogradouroAssembler mapper;

    @InjectMocks
    TipoLogradouroService service;

    @Test
    @DisplayName("Teste Unitário - TipoLogradouroService.buscarPorParametros(TipoLogradouroFiltroDTO filtro, Pageable pageable)")
    void buscarPorParametrosTest(){
        val entity =
                TipoLogradouro.builder()
                        .id(1L).abreviacao("Teste abreviacao").descricao("Teste descricao")
                        .build();
        val tipoLogradouros = Arrays.asList(entity);

        val dtoRequest = new TipoLogradouroFiltroDTO();
        val page = new PageImpl<>(tipoLogradouros);
        val dtoResponse =
                TipoLogradouroDTO.builder()
                        .id(111L).abreviacao("Repsonse Teste").descricao("Response Teste")
                        .build();

        //quando chamar o repository, então retorna page
        lenient()
                .when(repository.findAll(TipoLogradouroSpecs.comFiltro(dtoRequest), page.getPageable()))
                .thenReturn(page);

        //quando chamar o mapper, então retorna o DTO
        lenient().when(mapper.toDTO(entity)).thenReturn(dtoResponse);

        val tipoLogradouroDTOS = service.buscarPorParametros(dtoRequest, page.getPageable());

        assertTrue(tipoLogradouroDTOS.getTotalPages() > 0);
        assertTrue(tipoLogradouroDTOS.getContent().size() > 0);
        assertTrue(tipoLogradouroDTOS.getContent().stream().filter(f -> f.getId() == 111L).count() > 0);

    }
}
```

### Configuração
* Incluir a dependência do próprio Spring teste, ela importa o Junit e o Mockito.
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
    <!--exclui o junit 4-->
    <exclusions>
        <exclusion>
            <groupId>org.junit.vintage</groupId>
            <artifactId>junit-vintage-engine</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
* Criar o plugin para geração dos reports do testes:
  * Report web é gerado em: ```/target/site```
  * Demais reports em: ```/target/surefire-reports```
```
<!--Teste - salva os reports dos teste em: /target/site e /target/surefire-reports-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.2</version>
    <configuration>
        <encoding>UTF-8</encoding>
    </configuration>
</plugin>
```

### Referência
* [https://junit.org/junit5/docs/current/user-guide/](https://junit.org/junit5/docs/current/user-guide/)
* [https://www.baeldung.com/junit-5](https://www.baeldung.com/junit-5)
