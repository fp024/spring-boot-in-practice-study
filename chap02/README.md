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





## 의견

* ...

  

## Ai와의 대화

* ...



## 정오표

* ...





## 사용 아이콘

💡⬜✅✔✨👍😅



