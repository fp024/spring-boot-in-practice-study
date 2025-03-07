package org.springboot.practice.repository;

import org.springboot.practice.model.Course;
import org.springboot.practice.repository.projection.DescriptionOnly;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository
    extends CrudRepository<Course, Long>, QuerydslPredicateExecutor<Course> {

  Iterable<DescriptionOnly> getCourseByName(String name);
}
