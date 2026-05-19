package org.springboot.practice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springboot.practice.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CourseTrackerSpringBootApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(CourseTrackerSpringBootApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    User user1 = new User("sbip01", "sbip");
    Set<ConstraintViolation<User>> violations = validator.validate(user1);
    log.error("Password for user1 do not adhere to the password policy");
    violations.forEach(
        constraintViolation ->
            log.error("Violation details: [{}].", constraintViolation.getMessage()));

    User user2 = new User("sbip02", "Sbip01$4UDfg");
    violations = validator.validate(user2);
    if (violations.isEmpty()) {
      log.info("Password for user2 adhere to the password policy");
    }

    User user3 = new User("sbip03", "Sbip01$4UDfggg");
    violations = validator.validate(user3);
    log.error("Password for user3 violates maximum repetitive rule");
    violations.forEach(
        constraintViolation ->
            log.error("Violation details: [{}].", constraintViolation.getMessage()));

    User user4 = new User("sbip04", "Sbip014UDfgg");
    // 이부분은 동일 문자 반복에 대한 위반보단,
    // 특수문자가 한 글자 이상 포함되었는지에 대한 오류 확인이므로, g 4회반복을 2회반복으로 해야겠다.
    violations = validator.validate(user4);
    log.error("Password for user4 violates special character rule");
    violations.forEach(
        constraintViolation ->
            log.error("Violation details: [{}].", constraintViolation.getMessage()));
  }
}
