# 01. 스프링 부트 시작하기

> Boot가 이제는 익숙해진 편이지만.. 한번쭈욱 나가는 것도 괜찮을 것 같아서 시작은 했는데... 
>
> 끝까지 보는게 중요할 것 같다. 💪



## 진행

### 저자님 예제

* https://github.com/spring-boot-in-practice/repo/tree/main/ch01



⬜ 한번 읽어보기



### 💡 안전 종료 설정

```yaml
server:
  shutdown: graceful
# ...
spring:
  lifecycle:
    timeout-per-shutdown-phase: 1m
```

더이상의 요청은 받지 않지만, 이미 처리 중인 요청은 완료를 보장하는 설정



### 💡 스프링 부트 액추에이터

* http://localhost:8080/actuator

  ```json
  {
    "_links": {
      "self": {
        "href": "http://localhost:8080/actuator",
        "templated": false
      },
      "health": {
        "href": "http://localhost:8080/actuator/health",
        "templated": false
      },
      "health-path": {
        "href": "http://localhost:8080/actuator/health/{*path}",
        "templated": true
      }
    }
  }
  ```

  * http://localhost:8080/actuator/health

### 💡 Graceful 동작 확인

bootRun으로 실행시키고 해당 콘솔에서 Ctrl + C를 누르면 Graceful 동작 확인을 할 수가 없어서, 

shutdown 엔드 포인트 노출시키고 Curl로 확인해보았다.

```yaml
management:
  endpoint:
    shutdown:
      access: unrestricted
  endpoints:
    web:
      exposure:
        include:
          - health
          - shutdown
```

#### curl 명령 실행

```sh
$ curl -X POST http://localhost:8080/actuator/shutdown
{"message":"Shutting down, bye..."}
```

#### 로그 확인

```
17:11:56.088 [http-nio-8080-exec-3] DEBUG org.springframework.web.servlet.DispatcherServlet - POST "/actuator/shutdown", parameters={}
17:11:56.093 [http-nio-8080-exec-3] DEBUG o.s.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping - Mapped to Actuator web endpoint 'shutdown'
17:11:56.106 [http-nio-8080-exec-3] DEBUG o.s.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor - Read "application/octet-stream" to []
17:11:56.116 [http-nio-8080-exec-3] DEBUG o.s.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor - Using 'application/vnd.spring-boot.actuator.v3+json', given [*/*] and supported [application/vnd.spring-boot.actuator.v3+json, application/vnd.spring-boot.actuator.v2+json, application/json]
17:11:56.139 [http-nio-8080-exec-3] DEBUG o.s.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor - Writing [org.springframework.boot.actuate.context.ShutdownEndpoint$ShutdownDescriptor@bfd82b0]
17:11:56.149 [http-nio-8080-exec-3] DEBUG org.springframework.web.servlet.DispatcherServlet - Completed 200 OK
17:11:56.631 [Thread-7] INFO  org.springframework.boot.web.embedded.tomcat.GracefulShutdown - Commencing graceful shutdown. Waiting for active requests to complete
17:11:56.642 [tomcat-shutdown] INFO  org.springframework.boot.web.embedded.tomcat.GracefulShutdown - Graceful shutdown complete
...
```

**💡 노출된 shutdown 엔드포인트에 대해서는 Spring Security와 연동해서 접근 제한을 설정해야한다.**



## 의견

저자님은 Maven 쓰시는데, 나는 Gradle을 사용했을 때, 이전 버전 프로젝트를 구분해서 만들기가 편해서 Gradle 쓰기로 했다. 😅

1장은 이미 다 알거라고 생각했지만... 그래도 꽤 참고할만한 사항이 있었다. 👍



## Ai와의 대화

* ...



## 정오표

* ...





## 사용 아이콘

💡⬜✅✔✨👍😅



