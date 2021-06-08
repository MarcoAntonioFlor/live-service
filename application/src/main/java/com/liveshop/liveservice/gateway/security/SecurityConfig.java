package com.liveshop.liveservice.gateway.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liveshop.liveservice.gateway.security.propertie.SecurityPropertie;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final ObjectMapper objectMapper;
  private final SecurityFilter tokenAuthenticationFilter;
  private final SecurityPropertie securityPropertie;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().formLogin().disable()
        .httpBasic().disable().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint())
        .and().authorizeRequests()
        .antMatchers(getAllowedPublicApis()).permitAll()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and()
        .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  public AuthenticationEntryPoint restAuthenticationEntryPoint() {
    return (httpServletRequest, httpServletResponse, e) -> {
      Map<String, Object> errorObject = new HashMap<>();
      errorObject.put("message", "Unauthorized access of protected resource, invalid credentials");
      errorObject.put("error", HttpStatus.UNAUTHORIZED);
      errorObject.put("code", HttpStatus.UNAUTHORIZED.value());
      httpServletResponse.setContentType("application/json;charset=UTF-8");
      httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
      httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorObject));
    };
  }

  private String[] getAllowedPublicApis(){
    return securityPropertie
        .getAllowedPublicApis()
        .toArray(String[]::new);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(securityPropertie.getAllowedOrigins());
    configuration.setAllowedMethods(securityPropertie.getAllowedMethods());
    configuration.setAllowedHeaders(securityPropertie.getAllowedHeaders());
    configuration.setAllowCredentials(securityPropertie.isAllowCredentials());
    configuration.setExposedHeaders(securityPropertie.getExposedHeaders());
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
