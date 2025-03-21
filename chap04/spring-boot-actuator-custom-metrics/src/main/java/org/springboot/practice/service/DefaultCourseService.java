package org.springboot.practice.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Timer;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springboot.practice.model.Course;
import org.springboot.practice.repository.CourseRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultCourseService implements CourseService {

  private final CourseRepository courseRepository;
  private final Counter createCourseCounter;
  private final Timer createCoursesTimer;
  private final DistributionSummary distributionSummary;

  @SneakyThrows
  public Course createCourse(Course course) {

    /* 아래 문장은 생성된 코스의 수를 세는 데 사용됩니다. */
    createCourseCounter.increment();
    // return courseRepository.save(course);

    distributionSummary.record(course.getRating());

    /*
      아래 문장은 타이머를 사용하여 Course를 데이터베이스에 저장하는 데 걸리는 시간을 캡처하는 데 사용됩니다.
    */
    return createCoursesTimer.recordCallable(() -> courseRepository.save(course));
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

  @Override
  public long count() {
    return courseRepository.count();
  }
}
