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



### 3.2.3 기법: 스프링 부트 애플리케이션에서 관계형 데이터베이스 초기화

💡 **h2 2.3.232 버전**에서는  INT(15), INT(1) 등으로 자리수 정보를 넣으면 문법 오류가 발생한다.

```sql
CREATE TABLE COURSES
(
    id           INT NOT NULL,
    name         VARCHAR(100) NOT NULL,
    category     VARCHAR(20) NOT NULL,
    rating       TINYINT NOT NULL,
    description  VARCHAR(1000) NOT NULL,
    PRIMARY KEY (id)
);
```

* 💡 **INT(1)**로 되어있던 rating은 **TINYINT**로 변경했다.



### 3.3.1 기법: 스프링 데이터 JPA를 사용해서 도메인 객체를 관계형 데이터베이스에

### 💡 id로 정수를 쓸 경우 레퍼타입을 사용할 것!

* [Question about delete behavior when using Spring Data JPA and the entity’s ID is a primitive type · Issue #3107 · spring-projects/spring-data-jpa](https://github.com/spring-projects/spring-data-jpa/issues/3107)

  > 원시 타입을 쓰면 int, long의 경우 값이 새로운 엔티티라고 판단을 하게되서, 
  >
  > 만약 DB에 ID가 0인 데이터가 있을 경우 삭제를 못하게된다.

p104를 보면.. Course 엔티티의 ID가 레퍼 타입이 맞는데, 예제 소스는 수정이 안되어있던 것 같다.

* h2에서도 `@GeneratedValue(strategy = GenerationType.IDENTITY)`로 지정하면 1부터 시작한다.



Optional 결과 반환은 isEqualTo 보단 contains()로 검사하는게 좋다고 한다.

```java
assertThat(courseRepository.findById(savedCourse.getId())) //
    .contains(course);
```

Optional의 isPresent() 결과도 직접 검사하는 메서드가 있다.

```java
assertThat(courseRepository.findById(savedCourse.getId())) //
    .isNotPresent();
```



### 3.4.2 기법: 관계형 데이터베이스에서 스프링 데이터 JPA를 사용한 커스텀 쿼리 메서드 정의

```java
Stream<Course> streamAllByCategory(String category);
```

스트림에 대해서 테스트를 할 때는 `@Transactional`을 설정해줘야한다.

```
org.springframework.dao.InvalidDataAccessApiUsageException: You're trying to execute a streaming query method without a surrounding transaction that keeps the connection open so that the Stream can actually be consumed; Make sure the code consuming the stream uses @Transactional or any other way of declaring a (read-only) transaction
```

```java
  @Transactional
  @Test
  void streamAllByCategory() {
    courseRepository.saveAll(getCourseList());
    assertThat(courseRepository.streamAllByCategory("Spring")).hasSize(3);
  }
```



### 3.4.4 PagingAndSortingRepository 인터페이스로 데이터 페이징 및 정렬

> sql 스크립트로 초기화할 때... resource에 schema.sql, data.sql 두가지 만들어놓고 설정을 ddl-auto: none 으로만 하면 되는구나... 난 예전에 jakarta jpa 프로퍼티 설정으로 처리했었는데... 😂

이번에 assertj의 테스트 방식에 Condition을 활용하는 부분..

```java
  @Test
  void givenDataAvailableWhenSortsFirstPageThenGetSortedSData() {
    Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")));
    Condition<Course> sortedFirstCourseCondition =
        new Condition<Course>() {
          @Override
          public boolean matches(Course course) {
            return course.getId() == 4
                && course.getName().equals("Cloud Native Spring Boot Application Development");
          }
        };
    assertThat(courseRepository.findAll(pageable)).first().has(sortedFirstCourseCondition);
  }
```

조회 결과 페이지의 첫번째 Course가 해당 조건을 가지는지 확인한다.

name이 C로시작하는것이 항상 오름차순 기준 첫번째이므로 그것을 검사하는 테스트 코드다.



### 3.7.1 기법: 관계형 데이터베이스에 저장된 도메인 객체를 QueryDSL로 관리

💡spring boot  3.x + gradle 환경에서는 다음과 같이 설정

````groovy
  implementation ("com.querydsl:querydsl-jpa") {
    artifact {
      classifier = 'jakarta'
    }
  }
  annotationProcessor (
    'jakarta.persistence:jakarta.persistence-api', 
    'jakarta.annotation:jakarta.annotation-api'
  )
  annotationProcessor ("com.querydsl:querydsl-apt") {
    artifact {
      classifier = 'jakarta'
    }
  }
````

코드 정리가 되면... 쿼리(Q)타입 도메인 생성을 위해 다음 명령을 실행해주자!

```sh
gradle clean classes
```









## 의견

나에게 유용했던 내용

* p99에 DB 플렛폼 ID를 파일이름에 포함시켜서 sql 파일들을 나눠서 만들수 있다는 점.



## Ai와의 대화

* ...



## 정오표

* ...





## 사용 아이콘

💡⬜✅✔✨👍😅



