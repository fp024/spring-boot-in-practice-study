package org.springboot.practice.security;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

  private final AccessDeniedHandler customAccessDeniedHandler;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            auth ->
                auth.requestMatchers(
                        "/webjars/**", //
                        "/images/**",
                        "/css/**",
                        "/h2-console/**",
                        "/accessDenied")
                    .permitAll()
                    .requestMatchers("/login") //
                    .permitAll()
                    //
                    .requestMatchers("/delete/**")
                    .hasRole("ADMIN")
                    //
                    .anyRequest()
                    .authenticated())
        .formLogin(
            form ->
                form.loginPage("/login") //
                    .permitAll())
        .exceptionHandling(conf -> conf.accessDeniedHandler(customAccessDeniedHandler));

    return http.build();
  }

  @Bean
  UserDetailsService userDetailsService(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
