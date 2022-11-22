package br.gov.rj.fazenda.email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.gov.rj.fazenda.email.security.filter.JwtTokenFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true, 
		securedEnabled = true, 
		jsr250Enabled = true)
public class SecurityConfig {

    @Value("${spring.security.debug:false}")
    boolean securityDebug;

	@Value("${cors.mapping}")
	public String mapping;
	
	@Value("${cors.origins}")
	public String origins;
	
	@Value("${cors.methods}")
	public String methods;
	
	@Value("${cors.headers}")
	public String headers;
    
	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
          .disable()
          .authorizeRequests()
			.antMatchers("/login/**")
				.anonymous()
			.antMatchers("/swagger-ui.html").permitAll()
			.antMatchers("/swagger-ui/**").permitAll()
			.antMatchers("/swagger-ui/**", "/javainuse-openapi/**").permitAll()
			.antMatchers("/v3/api-docs/swagger-config**").permitAll()
			.antMatchers("/v3/api-docs/**").permitAll()
	          .anyRequest()
	          	.authenticated()
       .and()
       		.sessionManagement()
       			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
  		.and()
  			.addFilterBefore( jwtTokenFilter() , UsernamePasswordAuthenticationFilter.class )
	    ;
        return http.build();
    }

    @Bean
	public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        
        config.addAllowedOrigin(origins);
        config.addAllowedHeader(headers);
        config.addAllowedMethod(methods);
       
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
