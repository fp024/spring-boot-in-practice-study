package org.springboot.practice.service;

import lombok.RequiredArgsConstructor;
import org.springboot.practice.model.Course;
import org.springboot.practice.repository.CourseRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseService {

  private final CourseRepository courseRepository;

  public Iterable<Course> getAvailableCourses() {
    return courseRepository.findAll();
  }
}
