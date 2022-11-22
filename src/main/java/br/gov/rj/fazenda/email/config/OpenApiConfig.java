package br.gov.rj.fazenda.email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Value("${name}")
	public String name;

	@Value("${description}")
	public String description;

	@Value("${version}")
	public String version;
	
	@Value("${cors.mapping}")
	public String mapping;
	
	@Value("${cors.origins}")
	public String origins;
	
	@Value("${cors.methods}")
	public String methods;
	
	@Value("${cors.headers}")
	public String headers;
	
	@Bean
	public OpenAPI api() {
		final String securitySchemeName = "bearerAuth";
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(
			            new Components()
			                .addSecuritySchemes(securitySchemeName,
			                    new SecurityScheme()
			                        .name(securitySchemeName)
			                        .type(SecurityScheme.Type.HTTP)
			                        .scheme("bearer")
			                        .bearerFormat("JWT")
			                )
			        )
				.info(new Info()
						.title(name)
						.version(version)
						.description(description)
						.termsOfService("http://swagger.io/terms")
						.license(new License().name("Apache 2.0")));
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(mapping)
                .allowedOrigins("http://localhost:4200")
				.allowedOrigins("http://lrhel8eap005v.sefnet.rj:8194/template-angular-web")
				.allowedOrigins(origins)
                .allowedMethods(methods)
                .allowedHeaders(headers)
                .allowCredentials(true);
            }
        };
    }

}
