package org.springboot.practice.security;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.ldap.LdapPasswordComparisonAuthenticationManagerFactory;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
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
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  DefaultSpringSecurityContextSource contextSource() {
    return new DefaultSpringSecurityContextSource(
        List.of("ldap://localhost:8389/"), //
        "dc=manning,dc=com" //
        );
  }

  @Bean
  AuthenticationManager authenticationManager(
      DefaultSpringSecurityContextSource contextSource, PasswordEncoder passwordEncoder) {
    // 기존 passwordCompare() 설정과 동일한 방식
    var factory =
        new LdapPasswordComparisonAuthenticationManagerFactory(contextSource, passwordEncoder);
    factory.setUserDnPatterns("uid={0},ou=people");
    factory.setPasswordAttribute("userPassword");

    // Role 정보를 LDAP에서 가져오도록 설정
    factory.setLdapAuthoritiesPopulator(
        new DefaultLdapAuthoritiesPopulator(contextSource, "ou=groups") {
          {
            setGroupSearchFilter("member={0}");
            setGroupRoleAttribute("cn");
          }
        });

    return factory.createAuthenticationManager();
  }
}
