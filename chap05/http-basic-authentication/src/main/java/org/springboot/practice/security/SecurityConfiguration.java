package org.springboot.practice.security;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
                    //
                    .requestMatchers("/delete/**")
                    .hasRole("ADMIN")
                    //
                    .anyRequest()
                    .authenticated())
        .httpBasic(Customizer.withDefaults())
        // 💡 HTTP Basic 인증은 브라우저가 Authorization 헤더를 계속 보내는 방식이다.
        //    예: Authorization: Basic YWRtaW46cHcwMA==
        //        → "admin:pw00" 을 Base64로 인코딩한 값
        //
        //    로그아웃으로 서버 세션을 무효화하더라도,
        //    브라우저가 Basic 인증 정보를 계속 보관하고 있으면
        //    다음 요청에서 Authorization 헤더를 다시 보내기 때문에 다시 인증된다.
        //
        //    서버는 클라이언트 브라우저가 저장한 HTTP Basic 인증 정보를
        //    직접 초기화할 수 없다.
        //
        //    그래서 브라우저를 닫거나 인증 정보를 지우기 전까지는
        //    로그아웃해도 자동 로그인되는 것처럼 보일 수 있다.
        .logout(conf -> conf.logoutSuccessUrl("/").invalidateHttpSession(true))
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
