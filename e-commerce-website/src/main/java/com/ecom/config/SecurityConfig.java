package com.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private AuthSuccessHandlerImpl authSuccessHandlerImpl;

	@Autowired
	@Lazy
	private AuthFailureHandlerImpl authenticationFailureHandler;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.csrf(csrf->csrf.disable()).cors(cors->cors.disable())
				.authorizeHttpRequests(req->req.requestMatchers("/userindex").hasRole("USER")
						.requestMatchers("/admin/users").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST,"/reset-password").permitAll()
						.requestMatchers(HttpMethod.POST,"/forgot-password").permitAll()
						.requestMatchers(HttpMethod.GET,"/forgot-password").permitAll()
//						.requestMatchers(HttpMethod.GET,"/reset-password").permitAll()
						.requestMatchers("/**").permitAll()
						.anyRequest().authenticated())
				.formLogin(form->form.loginPage("/login")
						.loginProcessingUrl("/login")
//						.defaultSuccessUrl("/")
						.failureHandler(authenticationFailureHandler)
						.successHandler(authenticationSuccessHandler))
				.logout(logout->logout.permitAll());

		return http.build();
	}


	}
