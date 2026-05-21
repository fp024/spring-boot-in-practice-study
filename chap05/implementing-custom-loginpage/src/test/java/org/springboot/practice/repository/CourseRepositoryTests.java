package org.springboot.practice.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springboot.practice.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CourseRepositoryTests {

  @Autowired private CourseRepository courseRepository;

  @Test
  void test() {
    Course course = new Course();
    course.setName("Course Name");
    course.setCategory("Spring");
    course.setRating(1);
    course.setDescription("Description");

    courseRepository.save(course);
  }
}
