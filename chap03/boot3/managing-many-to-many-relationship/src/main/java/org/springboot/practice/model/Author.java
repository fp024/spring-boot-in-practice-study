package org.springboot.practice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@Entity(name = "AUTHOR")
@Table(name = "AUTHORS")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String bio;

  @ToString.Exclude
  @ManyToMany
  @JoinTable(
      name = "authors_courses",
      joinColumns = {
        @JoinColumn(
            name = "author_id",
            referencedColumnName = "id",
            nullable = false,
            insertable = false,
            updatable = false)
      },
      inverseJoinColumns = {
        @JoinColumn(
            name = "course_id",
            referencedColumnName = "id",
            nullable = false,
            insertable = false,
            updatable = false)
      })
  private Set<Course> courses = new HashSet<>();

  public Author(String name, String bio) {
    this.name = name;
    this.bio = bio;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBio() {
    return bio;
  }

  public Set<Course> getCourses() {
    return courses;
  }
}
