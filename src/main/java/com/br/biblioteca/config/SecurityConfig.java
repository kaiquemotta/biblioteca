package com.br.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.br.biblioteca.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter filter;

	public SecurityConfig(JwtAuthenticationFilter filter) {
		this.filter = filter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((requests) -> {

			requests.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/webjars/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/index.html**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-resources/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui.html")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger.json")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll()

					.requestMatchers(new AntPathRequestMatcher("/usuario")).permitAll()
					.requestMatchers(new AntPathRequestMatcher("/auth")).permitAll()
					.requestMatchers(new AntPathRequestMatcher("/autor/**")).authenticated()
					.requestMatchers(new AntPathRequestMatcher("/genero/**")).authenticated()
					.requestMatchers(new AntPathRequestMatcher("/livro/**")).authenticated().anyRequest()
					.authenticated();

		}).formLogin((form) -> form.loginPage("/login").permitAll()).logout((logout) -> logout.permitAll())
				.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationProvider authProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
		daoAuthProvider.setUserDetailsService(userDetailsService);
		daoAuthProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
