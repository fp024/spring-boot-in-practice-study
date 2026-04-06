 

# Spring Boot in Practice - Study

>  - 실전 스프링 부트 - 스터디



## 책 정보

### 저자

* **저자:** 솜나트 무시브
* **역자:** 오명운

### 판매처

* yes24
  * https://www.yes24.com/Product/Goods/122002340
* 교보문고
  * https://product.kyobobook.co.kr/detail/S000208713876
* 알라딘
  * https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=323427516




### 소스코드

* 저자님 리포지토리

  * https://github.com/spring-boot-in-practice/repo





## 진행 방향

✨  책의 Boot 버전은 2.6.3 기준이지만, 3.x기준으로 예제를 바꾸면서 진행해보자!.

💡필요한 경우만 버전을 2.x의 마지막 버전인, 2.7.18로 별도 프로젝트를 만들어서 사용하도록 하자!




## 목차

### 01. [스프링 부트 시작하기](chap01)

### 02. [스프링 부트 공통 작업](chap02)

### 03. [스프링 데이터를 사용한 데이터베이스 접근](chap03)

### 04. [스프링 자동 구성과 액추에이터](chap04)

### 05. [스프링 부트 애플리케이션 보안](chap05)

### 06. 스프링 시큐리티 응용

### 07. 스프링 부트 RESTful 웹 서비스 개발

### 08. 리액티브 스프링 부트 애플리케이션 개발

### 09. 스프링 부트 애플리케이션 배포

### 10. 스프링 부트와 코틀린, 네이티브 이미지, GraphQL

### 부록 A 스프링 이니셜라이저와 스프링 부트 CLI

### 부록 B 스프링 MVC와 타임리프 템플릿 엔진



## pnpm 스크립트 명령어

루트 디렉터리의 `package.json`에 정의된 유틸리티 스크립트 명령어입니다.

### 코드 포맷팅

| 명령어 | 설명 |
|--------|------|
| `pnpm format` | Prettier를 사용하여 HTML, CSS, JS, JSON 파일을 포맷팅합니다. 루트 또는 하위 프로젝트에서 실행 가능합니다. |

### 패키지 관리 (루트에서 실행)

| 명령어 | 설명 |
|--------|------|
| `pnpm install-all` | 모든 하위 프로젝트의 의존성을 설치합니다. |
| `pnpm update-all` | 모든 하위 프로젝트의 의존성을 `package.json`에 명시된 버전 범위 내에서 업데이트합니다. |
| `pnpm update-latest` | 모든 하위 프로젝트의 의존성을 최신 버전으로 업데이트합니다. (메이저 버전 포함) |
| `pnpm init-all-projects` | 모든 하위 프로젝트의 VSCode 실행환경의 Java / 테스트 설정<br /> (`-parameters` VM옵션 설정, mockito 버전 동기화) |



## 프로젝트 실행 / 테스트
> ✨ Gradle 멀티 프로젝트로 구성했는데 개별 실행 방법 정리 했다. 

### 특정 예제 실행 (Spring Boot 앱)

```bash
# 패턴: ./gradlew :경로:태스크
./gradlew :chap01:chap01-boot3:bootRun
./gradlew :chap02:boot3:bean-validation:bootRun
./gradlew :chap04:spring-boot-actuator:bootRun
```

### 특정 예제 빌드

```bash
./gradlew :chap02:boot3:bean-validation:build
```

### 특정 예제 테스트

```bash
./gradlew :chap03:boot3:configuring-mongodb-database:test
```

### 해당 디렉토리에서 실행

```bash
cd chap02/boot3/bean-validation
./gradlew bootRun
```

> **참고**: 경로 구분은 `:` 사용 (settings.gradle의 include 경로와 동일)



## ✨ 도움 문서 모음

* ...



## 후기

* ...



## 완료

* ....