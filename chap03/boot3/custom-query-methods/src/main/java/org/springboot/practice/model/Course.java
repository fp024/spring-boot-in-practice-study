package org.springboot.practice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "COURSES")
public class Course {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "CATEGORY")
  private String category;

  @Column(name = "RATING")
  private int rating;

  @Column(name = "DESCRIPTION")
  private String description;

  public Course(String name, String category, int rating, String description) {
    this.name = name;
    this.category = category;
    this.rating = rating;
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Course)) return false;
    Course course = (Course) o;
    return Objects.equals(name, course.name) && Objects.equals(category, course.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, category);
  }

  @Override
  public String toString() {
    return "Course{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", category='"
        + category
        + '\''
        + ", rating="
        + rating
        + ", description='"
        + description
        + '\''
        + '}';
  }
}
