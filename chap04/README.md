# 04. 스프링 자동 구성과 액추에이터

> 일단 내가 액추에이터에 대해서는 익숙하진 않긴하다. 😅



## 진행

### 저자님 예제

* https://github.com/spring-boot-in-practice/repo/tree/main/ch04

⬜ 한번 읽어보기



* ~ 167: 자동 구성, DevTools관련해서 간단하게 읽어봤다.

### 4.2 스프링 부트 개발자 도구

* https://docs.spring.io/spring-boot/reference/using/devtools.html#using.devtools
  * 프로퍼티 기본값
    * https://docs.spring.io/spring-boot/reference/using/devtools.html#using.devtools.property-defaults
  * 자동 재시작
    * https://docs.spring.io/spring-boot/reference/using/devtools.html#using.devtools.restart
  * 라이브 리로드
    * https://docs.spring.io/spring-boot/reference/using/devtools.html#using.devtools.livereload



#### 4.3.1 기법: 커스텀 스프링 부트 실패 분석기 생성

> 예제: [custom-failure-analyzer](custom-failure-analyzer)

##### `@SuppressWarnings("serial")`를 사용하면 다음과 같은 경고가 나오면서 어노테이션을 지우라고 한다.

```
At least one of the problems in category 'serial' is not analysed due to a compiler option being ignoredJava(1102)
```

Java 21 + VSCode 환경에서 이미 무시가 되고 있기 때문에, 경고 억제를 일부러 할 필요가 없다는 것 같다.

💡 **spring.factories**를 **src/resources/META-INF**에 두어도 잘 동작한다.







## 의견

나에게 유용했던 내용

* ...




## Ai와의 대화

* ...



## 정오표

* ...





## 사용 아이콘

💡⬜✅✔✨👍😅



