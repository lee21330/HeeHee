package com.shinhan.heehee.config;

import com.shinhan.heehee.service.CustomUserDetailsService;
import com.shinhan.heehee.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.shinhan.heehee")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CustomClientRegistrationRepository clientRegistrationRepository() {
    	return new CustomClientRegistrationRepository();
    }

    @Bean
    public JwtLogoutSuccessHandler jwtLogoutSuccessHandler() {
        return new JwtLogoutSuccessHandler();
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtUtil, customUserDetailsService);
    }
    
    @Bean
    public AuthenticationSuccess authenticationSuccessHandler() {
        return new AuthenticationSuccess(jwtUtil);
    }

    @Bean
    public AuthenticationFailure authenticationFailureHandler() {
        return new AuthenticationFailure();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService); // customUserDetailsService 등록
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .httpBasic().disable()
            .sessionManagement()
            	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint())
                .accessDeniedHandler(customAccessDeniedHandler())
                .and()
            .authorizeRequests()
                .antMatchers("/auc").authenticated()
                .antMatchers("/auc/**").authenticated()
                .antMatchers("/user/**").permitAll()
                .and()
            .logout()
                .logoutUrl("/user/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(jwtLogoutSuccessHandler())
                .and()
            .oauth2Login()
            
            .authorizationEndpoint()
            	.baseUri("/oauth2/authorize")
            	.and()
            .redirectionEndpoint()
                .baseUri("/login/oauth2/code/*")
                .and()
            .defaultSuccessUrl("")
            .clientRegistrationRepository(clientRegistrationRepository())
            
            .and()
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}