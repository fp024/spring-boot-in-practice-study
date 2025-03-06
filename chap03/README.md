# 03. 스프링 데이터를 사용한 데이터베이스 접근

> ...



## 진행

### 저자님 예제

* https://github.com/spring-boot-in-practice/repo/tree/main/ch03



⬜ 한번 읽어보기



### 3.2.1 기법: 스프링 부트 애플리케이션에서 관계형 데이터베이스 연동 설정

H2 콘솔을 활성화하면 다음 URL로 접근이 가능하다.

주소도 ` jdbc:h2:mem:sbipdb`로 입력하면 된다.

* http://localhost:8080/h2-console/



### 3.2.2 기법: 스프링 부트 애플리케이션 몽고DB 설정

Spring Boot 3부터 flapdoodle의 embedded mongodb 라이브러리가 기본 디펜던시에서 제외되어 버전 설정은 따로 해줘야한다.

* https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo.spring
  * 버전은 내가 그동안 스터디에서 쓴 버전을 사용했는데, 자동 다운로드 되서 잘 동작함을 확인했다.

```yml
de.flapdoodle.mongodb.embedded.version: 5.0.9
```

**디펜던시** (toml에 등록)

```groovy
runtimeOnly (libs.embedded.mongo.spring3x)
```







## 의견

* ...



## Ai와의 대화

* ...



## 정오표

* ...





## 사용 아이콘

💡⬜✅✔✨👍😅



