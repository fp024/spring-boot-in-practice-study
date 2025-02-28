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



## 의견

저자님은 Maven 쓰시는데, 나는 Gradle을 사용했을 때, 이전 버전 프로젝트를 구분해서 만들기가 편해서 Gradle 쓰기로 했다. 😅

1장은 이미 다 알거라고 생각했지만... 그래도 꽤 참고할만한 사항이 있었다. 👍



## Ai와의 대화

* ...



## 정오표

* ...





## 사용 아이콘

💡⬜✅✔✨👍😅



