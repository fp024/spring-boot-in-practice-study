package org.springboot.practice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "COURSES")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course {

  public Course(String name, String category, int rating, String description) {
    this.name = name;
    this.category = category;
    this.rating = rating;
    this.description = description;
  }

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @EqualsAndHashCode.Include
  @Column(name = "NAME")
  private String name;

  @EqualsAndHashCode.Include
  @Column(name = "CATEGORY")
  private String category;

  @Column(name = "RATING")
  private int rating;

  @Column(name = "DESCRIPTION")
  private String description;
}
