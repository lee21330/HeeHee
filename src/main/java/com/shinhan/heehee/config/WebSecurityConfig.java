package com.shinhan.heehee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	/*
	 * private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	 * private final JwtAuthenticationFilter jwtRequestFilter;
	 * 
	 * @Override protected void configure(HttpSecurity httpSecurity) throws
	 * Exception { httpSecurity.csrf().disable();
	 * httpSecurity.headers().frameOptions().disable();
	 * 
	 * httpSecurity .authorizeRequests().anyRequest().permitAll() .and()
	 * .exceptionHandling() .authenticationEntryPoint(jwtAuthenticationEntryPoint)
	 * .and()
	 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 * 
	 * httpSecurity.addFilterBefore(jwtRequestFilter,
	 * UsernamePasswordAuthenticationFilter.class); }
	 * 
	 * @Bean public BCryptPasswordEncoder encodePassword() { return new
	 * BCryptPasswordEncoder(); }
	 */
	
}
