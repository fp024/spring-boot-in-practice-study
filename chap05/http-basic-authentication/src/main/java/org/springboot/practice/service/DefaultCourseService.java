package org.springboot.practice.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springboot.practice.model.Course;
import org.springboot.practice.repository.CourseRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultCourseService implements CourseService {

  private final CourseRepository courseRepository;

  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  public Optional<Course> findCourseById(Long courseId) {
    return courseRepository.findById(courseId);
  }

  public Iterable<Course> findAllCourses() {
    return courseRepository.findAll();
  }

  public Course updateCourse(Course course) {
    return courseRepository.save(course);
  }

  public void deleteCourseById(Long courseId) {
    courseRepository.deleteById(courseId);
  }
}
