# 02. 스프링 부트 공통 작업

> ...



## 진행

### 저자님 예제

* https://github.com/spring-boot-in-practice/repo/tree/main/ch02



⬜ 한번 읽어보기



### 2.1.3 환경 설정 파일

#### gradle 빌드

```sh
gradle clean bootJar
```

#### Jar 실행

```sh
java -jar build/libs/config-data-file-0.0.1-SNAPSHOT.jar
```

#### 명령 실행 경로 이하에 data/sbip.yml 설정 후 실행

```sh
java -jar build/libs/config-data-file-0.0.1-SNAPSHOT.jar --spring.config.location=data/sbip.yml
```

#### 프로필을 지정하여 실행

```sh
java -jar build/libs/config-data-file-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```



>  특별히 설정을 하지 않고 jar를 실행했을 때.. 기본이 test로 실행되는 줄 알고 착각했다. 메인 application.yml에 test로 활성화 상태였음. 😅
>
> ```yml
> spring:
>   ...
>   profiles:
>     active:
>       - test
> ```





## 의견

* ...

  

## Ai와의 대화

* ...



## 정오표

* ...





## 사용 아이콘

💡⬜✅✔✨👍😅



