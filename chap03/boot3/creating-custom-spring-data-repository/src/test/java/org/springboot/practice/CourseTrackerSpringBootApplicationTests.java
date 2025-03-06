package org.springboot.practice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springboot.practice.model.Course;
import org.springboot.practice.repository.CustomizedCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CourseTrackerSpringBootApplicationTests {

  @Autowired private CustomizedCourseRepository customizedCourseRepository;

  @Test
  void givenCreateCourseWhenFindAllCoursesThenExpectOneCourse() {
    Course course =
        new Course(
            "Rapid Spring Boot Application Development",
            "Spring",
            4,
            "'Spring Boot gives all the power of the Spring Framework without all of the"
                + " complexities");
    customizedCourseRepository.save(course);
    assertThat(Arrays.asList(customizedCourseRepository.findAll()).size()).isEqualTo(1);
  }
}
