package com.ValuedIn.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.ValuedIn.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RequestSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final AuthenticationService authenticationService;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Override
  public  void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().cors().and()
        .authorizeRequests().antMatchers("/api/auth/login", "/api/auth/register").permitAll()
        .anyRequest().authenticated().and().addFilter(new AuthorizationFilter(authenticationManagerBean(), authenticationService))
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and().sessionManagement().sessionCreationPolicy(STATELESS);

  }

}
