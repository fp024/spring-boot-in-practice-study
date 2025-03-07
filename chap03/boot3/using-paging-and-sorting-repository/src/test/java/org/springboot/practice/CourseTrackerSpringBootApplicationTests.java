package org.springboot.practice;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springboot.practice.model.Course;
import org.springboot.practice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@DataJpaTest
class CourseTrackerSpringBootApplicationTests {

  @Autowired private CourseRepository courseRepository;

  @Test
  void givenDataAvailableWhenLoadFirstPageThenGetFiveRecords() {
    Pageable pageable = PageRequest.of(0, 5);
    assertThat(courseRepository.findAll(pageable)).hasSize(5);
    assertThat(pageable.getPageNumber()).isZero();

    Pageable nextPageable = pageable.next();
    assertThat(courseRepository.findAll(nextPageable)).hasSize(4);
    assertThat(nextPageable.getPageNumber()).isEqualTo(1);
  }

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

  @Test
  void givenDataAvailableWhenApplyCustomSortThenGetSortedResult() {
    Pageable customSortPageable =
        PageRequest.of(0, 5, Sort.by("Rating").descending().and(Sort.by("name")));
    Condition<Course> customSortFirstCourseCondition =
        new Condition<Course>() {
          @Override
          public boolean matches(Course course) {
            return course.getId() == 2
                && course.getName().equals("Getting Started with Spring Security DSL");
          }
        };
    assertThat(courseRepository.findAll(customSortPageable))
        .first()
        .has(customSortFirstCourseCondition);
  }
}
