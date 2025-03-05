# 02. 스프링 부트 공통 작업

> ...



## 진행

### 저자님 예제

* https://github.com/spring-boot-in-practice/repo/tree/main/ch02



✅ 한번 읽어보기



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

* https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Config-Data-Migration-Guide#profile-specific-documents

> 예를 들어 다중 문서 YAML 파일에서 spring.profiles 속성을 사용하는 경우 spring.config.activate.on-profile로 마이그레이션해야 합니다. 이전 속성과 마찬가지로 속성을 적용하기 위해 활성화해야 하는 프로필 목록을 지정할 수 있습니다. (prod & cloud)와 같은 프로필 표현식을 사용할 수도 있습니다.
>
> 예를 들어, 다음 application.yaml이 있는 경우:
>
> ```yml
> spring:
>   profiles: "prod"
> secret: "production-password"
> ```
>
> 이는 다음과 같이 마이그레이션됩니다.
>
> ```yml
> spring:
>   config:
>     activate:
>       on-profile: "prod"
> secret: "production-password"
> ```
>
> 💡역자님 각주에 이렇게 바꾸는 걸 추천하는데, 그렇긴 그러네..  위대로라면 별도 설정이 없으면 prod가 기본으로 설정됨.  아래는 prod 조건이 맞을 때만 실행됨.. 그러고보니 아래가 나은 것 같기도 하다..😅



### 2.1.4 운영체제 환경 변수

#### git-bash 환경에서는 다음과 같이 실행해주면 ...

```sh
APP_TIMEOUT=50 gradle clean bootRun
```

**결과**

```
...
21:04:12.237 [restartedMain] INFO  org.springboot.practice.SpringBootAppDemoApplication - Started SpringBootAppDemoApplication in 0.874 seconds (process running for 1.21)
21:04:12.241 [restartedMain] INFO  org.springboot.practice.SpringBootAppDemoApplication - Configured application timeout value: 50
```

OS 환경 변수 값을 잘 읽음을 확인할 수 있었다.

Windows라면... (run.bat)  set으로 환경변수를 설정한다음 실행해주면 된다.

```bat
@echo off
set APP_TIMEOUT=50
gradle clean bootRun
```



### 2.2.1 기법: `@ConfigurationProperties`를 사용한 커스텀 프로퍼티 정의

**스프링 부트 설정 처리기**를 gradle에서는 다음과 같이 설정한다.

```groovy
annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'
```



#### Spring Boot 3 부터는 타입 레벨에서의 @ConstructorBinding은 더 이상 필요없음

* https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide#constructingbinding-no-longer-needed-at-the-type-level

> @ConstructorBinding은 더 이상 @ConfigurationProperties 클래스의 유형 수준에서 필요하지 않으므로 제거해야 합니다. 클래스 또는 레코드에 여러 생성자가 있는 경우, 속성 바인딩에 사용할 생성자를 나타내기 위해 생성자에서 여전히 사용될 수 있습니다.
>
> @ConfigurationProperties 클래스의 생성자에 종속성을 자동 연결하는 데 의존하고 있었다면, 이제 @Autowired로 주석을 달아 속성 바인딩의 대상으로 식별되는 것을 방지해야 합니다.

💡그래서 생성자에 붙였다.. 단일 생성자 하나라면 생략해도 된다고 한다.

그런데.. 이런 설정을 하고나니까... VSCode에서 application.properties에 설정한 커스텀 프로퍼티 내용들의 경고들이 해결되었다. 정확하게 인식이됨.

설명도 필드의 JavaDoc기준으로 설명이 자동으로 붙음 👍



### 2.3.1 기법: 스프링 부트 애플리케이션 시작 시 CommandLineRunner로 코드 실행

예제를 실행해 봤을 때, @Order(1)로 설정한 것이 제일 먼저 수행되고,

그 다음이 @Order(2), 그 다음은...  Boot 애플리케이션 클래스에 별도 지정한 Bean, 

마지막으로 Boot 애플리케이션이 Override한 run() 내부였다. 

그리고 gradle에서 커멘드라인 인자를 전달하려면 다음과 같이 실행한다.

```sh
gradle clean bootRun --args="args1 args2 args3"
```

**실행결과**

```
...
11:27:34.204 [restartedMain] INFO  org.springboot.practice.commandline.MyCommandLineRunner - MyCommandLineRunner executed as a Spring Component
11:27:34.204 [restartedMain] INFO  org.springboot.practice.commandline.AnotherCommandLineRunner - AnotherCommandLineRunner executed as a Spring Component
11:27:34.204 [restartedMain] INFO  org.springboot.practice.CourseTrackerApplication - CommandLineRunner executed as a bean definition with 3 arguments
11:27:34.204 [restartedMain] INFO  org.springboot.practice.CourseTrackerApplication - Argument: args1
11:27:34.204 [restartedMain] INFO  org.springboot.practice.CourseTrackerApplication - Argument: args2
11:27:34.204 [restartedMain] INFO  org.springboot.practice.CourseTrackerApplication - Argument: args3
11:27:34.204 [restartedMain] INFO  org.springboot.practice.CourseTrackerApplication - CourseTrackerApplication CommandLineRunner has executed
```

> * https://github.com/spring-boot-in-practice/repo/tree/main/ch02/command-line-runner/spring-boot-app-start
>
> 위의 예제 소스는 CommandLineRunner 적용 전의 기본 소스라서 추가하지 않아도 될 것 같다.



### 2.4.1 기법: 스프링 부트 애플리케이션의 기본 로깅 이해 및 커스터마이징

```yml
logging:
  pattern:
    console: "%clr(%d{dd-MM-yyyy HH:mm:ss.SSS}){yellow} %clr(${PID:- }){green} %magenta([%thread]) %highlight([%-5level]) %clr(%-40.40logger{39}){cyan} %msg%n"
  file:
    path: sbip/logs
  logback:
    rollingpolicy:
      max-history: 7 # 기본값 7일
      max-file-size: 10MB # 기본값 10MB
```

위 처럼 path를 sbip/logs로 설정하면 프로젝트 루트 기준으로 이하 경로가 생긴다.

그리고 디렉토리가 없을 경우, 디렉토리도 자동으로 생성이됨.

> 💡 스프링 부트는 기본적으로 날짜가 바뀌거나 로그파일 10MB가 되면 새로그 파일로 롤링함



### 2.4.2 기법: 스프링 부트 애플리케이션에서 Log4j2 사용

Gradle에서도 `spring-boot-starter-logging`에 대한 제외 설정을 해줘야한다.

```groovy
configurations {
  all {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
  }
}

dependencies {
  // ...
  implementation 'org.springframework.boot:spring-boot-starter-log4j2'
  // ...
}
```







## 의견

이번 장에서는 ...

`@ConfigurationProperties`를 사용해서 타입 안정성을 확보하는 방법이 제일 유용할 것 같다. 👍



## Ai와의 대화

* ...



## 정오표

* ...





## 사용 아이콘

💡⬜✅✔✨👍😅



