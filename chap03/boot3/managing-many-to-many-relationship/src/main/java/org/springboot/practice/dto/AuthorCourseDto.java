package org.springboot.practice.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class AuthorCourseDto {

  private Long id;
  private String authorName;
  private String courseName;
  private String description;
}
