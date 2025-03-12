package org.springboot.practice;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springboot.practice.model.Course;
import org.springboot.practice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CourseTrackerApplicationTests {

  @Autowired private CourseRepository courseRepository;

  @Test
  void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {
    Course course =
        new Course(
            "Rapid Spring Boot Application Development",
            "Spring",
            4,
            "'Spring Boot gives all the power of the Spring Framework without all of the"
                + " complexities");
    Course savedCourse = courseRepository.save(course);
    assertThat(courseRepository.findById(savedCourse.getId())) //
        .contains(course);
  }

  @Test
  void givenUpdateCourseWhenLoadTheCourseThenExpectUpdatedCourse() {
    Course course =
        new Course(
            "Rapid Spring Boot Application Development",
            "Spring",
            4,
            "'Spring Boot gives all the power of the Spring Framework without all of the"
                + " complexities");
    courseRepository.save(course);
    course.setRating(5);
    Course savedCourse = courseRepository.save(course);
    assertThat(courseRepository.findById(savedCourse.getId()).get().getRating()) //
        .isEqualTo(5);
  }

  @Test
  void givenDeleteCourseWhenLoadTheCourseThenExpectNoCourse() {
    Course course =
        new Course(
            "Rapid Spring Boot Application Development",
            "Spring",
            4,
            "'Spring Boot gives all the power of the Spring Framework without all of the"
                + " complexities");
    Course savedCourse = courseRepository.save(course);

    log.info("### id: {}", savedCourse.getId());

    assertThat(courseRepository.findById(savedCourse.getId())) //
        .contains(course);

    courseRepository.delete(course);
    assertThat(courseRepository.findById(savedCourse.getId())) //
        .isNotPresent();
  }
}
