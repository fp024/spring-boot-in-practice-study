package org.springboot.practice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "AUTHORS_COURSES")
@Table(name = "AUTHORS_COURSES")
public class AuthorCourse {
  @Id
  @Column(name = "author_id")
  private Long authorId;

  @Column(name = "course_id")
  private Long courseId;
}
