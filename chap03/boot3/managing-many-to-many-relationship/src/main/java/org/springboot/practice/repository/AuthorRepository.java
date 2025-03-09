package org.springboot.practice.repository;

import org.springboot.practice.dto.AuthorCourseDto;
import org.springboot.practice.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

  @Query(
      """
      SELECT new org.springboot.practice.dto.AuthorCourseDto(c.id, a.name, c.name, c.description)
        FROM AUTHOR a, COURSES c, AUTHORS_COURSES ac
       WHERE a.id = ac.authorId
         AND c.id = ac.courseId
         AND ac.authorId = ?1
      """)
  Iterable<AuthorCourseDto> getAuthorCourseInfo(long authorId);
}
