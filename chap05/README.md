# 05. 스프링 부트 애플리케이션 보안

> 보안은 여러번 해보긴 하였으나 다시 보면 더 좋다. 💪
>
> 저자님은 Boot 2.6.3 (Spring Security 5.6.1)이고, 나는 Boot 3.4.4 (Spring Security 6.4.4) 환경이여서,
>
> 보안 설정 부분은 수정해야할 부분이 많을 것 같다. 😅



## 진행

### 저자님 예제

* https://github.com/spring-boot-in-practice/repo/tree/main/ch05

⬜ 한번 읽어보기



### 5.2.1 기법: 스프링 부트 애플리케이션에서 스프링 시큐리티 활성화

> 예제: [introducing-spring-security](introducing-spring-security)

오랜만에 진행을 해봤는데...  예제가 BootStrap 4로 되어있는 것을 BootStrap 5로 바꿔서 모양 맞춰보느라고 거기서 시간이 많이 걸렸다. 😅

이번 예제는 스프링 부트 스프링 시큐리티 스타터에의한 자동 구성으로 설정된 기본 설정 내용만 동작 시켜보는 것이라서 별로 복잡한 내용은 아니였다.





### 5.3.1 기법: 스프링 부트 애플리케이션에서 스프링 시큐리티 로그인 페이지 커스터마이징

> 예제: [implementing-custom-loginpage](implementing-custom-loginpage)

일단 커스터마이징 로그인 페이지를 추가해보는 내용이다. 또 오랜만에 하니까 머리가 아프긴하네..😅

WebSecurity 설정으로 css나 이미지 리소스 경로 ignore 하는 부분은... Boot 시작시 경고가 노출되어서...

```java
    http.authorizeHttpRequests(
            auth ->
                auth.requestMatchers(
                        "/webjars/**", //
                        "/images/**",
                        "/css/**",
                        "/h2-console/**")
                    .permitAll()
                    ...
```

명시적으로 requestMatchers 설정에 추가했다. 



### 5.3.2 기법: 인메모리 인증 적용

> 예제: [implementing-inmemory-authentication](implementing-inmemory-authentication)









## 의견

나에게 유용했던 내용

* ...




## Ai와의 대화

* ...



## 정오표

* ...





## 사용 아이콘

💡⬜✅✔✨👍😅



