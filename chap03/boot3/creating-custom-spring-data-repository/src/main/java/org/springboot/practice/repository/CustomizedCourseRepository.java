package org.springboot.practice.repository;

import org.springboot.practice.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedCourseRepository extends BaseRepository<Course, Long> {}
