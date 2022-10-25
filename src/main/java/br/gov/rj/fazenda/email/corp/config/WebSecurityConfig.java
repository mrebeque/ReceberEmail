package br.gov.rj.fazenda.email.corp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.gov.rj.fazenda.email.corp.security.jwt.AuthEntryPointJwt;
import br.gov.rj.fazenda.email.corp.security.jwt.AuthTokenFilter;
import br.gov.rj.fazenda.email.corp.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${cors.mapping}")
	public String mapping;
	
	@Value("${cors.origins}")
	public String origins;
	
	@Value("${cors.methods}")
	public String methods;
	
	@Value("${cors.headers}")
	public String headers;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http//.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			
			/* Auth JWT */
			.antMatchers("/api/authenticate/**").permitAll()

			/* APIs */
			.antMatchers("/api/**").permitAll()
			
			/* Actuator */
			.antMatchers("/actuator/**").permitAll()
			
			/* H2 Console */
			.antMatchers("/h2-console/**").permitAll()
			
			/* Swagger */
			.antMatchers("/swagger-ui.html").permitAll()
			.antMatchers("/template-service/swagger-ui.html").permitAll()
			.antMatchers("/swagger-ui/**").permitAll()
			.antMatchers("/javainuse-openapi/**").permitAll()
			.antMatchers("/v3/api-docs/swagger-config**").permitAll()
			.antMatchers("/v3/api-docs/**").permitAll()

			.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
    public void configure(WebSecurity web)  {
        web.ignoring().antMatchers("/v3/api-docs",
                "/swagger-ui.html",
                "/swagger-ui/**",
				"/api/authenticate");
    }
	
	// Used by spring security if CORS is enabled.
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedOrigin("http://lrhel8eap005v.sefnet.rj:8140/template-angular");
		config.addAllowedOrigin("http://lrhel8eap005v.sefnet.rj:8140/template-angular-web");
		config.addAllowedOrigin("*");
		config.addAllowedOrigin(origins);
        config.addAllowedHeader(headers);
        config.addAllowedMethod(methods);
        source.registerCorsConfiguration(mapping, config);
        return new CorsFilter(source);
    }
}
