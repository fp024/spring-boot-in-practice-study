package org.springboot.practice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springboot.practice.ibp.DescriptionOnly;
import org.springboot.practice.repository.AuthorRepository;
import org.springboot.practice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseTrackerSpringBootApplicationTests {

  @Autowired private CourseRepository courseRepository;

  @Autowired private AuthorRepository authorRepository;

  @Test
  void whenCountAllCoursesThenExpectFiveCourses() {
    assertThat(authorRepository.getAuthorCourseInfo(2)).hasSize(3);
  }

  @Test
  void givenACourseAvailableWhenGetCourseByNameThenGetCourseDescription() {
    Iterable<DescriptionOnly> result =
        courseRepository.getCourseByName("Rapid Spring Boot Application Development");
    assertThat(result)
        .extracting("description")
        .contains(
            "Spring Boot gives all the power of the Spring Framework without all of the"
                + " complexity");
  }
}
