package org.springboot.practice.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {

  private int id;

  @NotEmpty(message = "Course name field can't be empty")
  private String name;

  @NotEmpty(message = "Course category field can't be empty")
  private String category;

  @Min(value = 1, message = "A course should have a minimum of 1 rating")
  @Max(value = 5, message = "A course should have a maximum of 5 rating")
  private int rating;

  @NotEmpty(message = "Course description field can't be empty")
  private String description;
}
