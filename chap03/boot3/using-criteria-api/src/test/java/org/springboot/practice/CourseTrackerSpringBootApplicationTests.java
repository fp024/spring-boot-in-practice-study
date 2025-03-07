package org.springboot.practice;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springboot.practice.model.Course;
import org.springboot.practice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseTrackerSpringBootApplicationTests {

  @Autowired private CourseRepository courseRepository;

  @Autowired private EntityManager entityManager;

  @Test
  void givenCoursesCreatedWhenLoadCoursesWithQueryThenExpectCorrectCourseDetails() {
    courseRepository.saveAll(getCourseList());

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Course> courseCriteriaQuery = criteriaBuilder.createQuery(Course.class);
    Root<Course> courseRoot = courseCriteriaQuery.from(Course.class);
    Predicate courseCategoryPredicate = criteriaBuilder.equal(courseRoot.get("category"), "Spring");
    courseCriteriaQuery.where(courseCategoryPredicate);
    TypedQuery<Course> query = entityManager.createQuery(courseCriteriaQuery);
    assertThat(query.getResultList().size()).isEqualTo(3);
  }

  private List<Course> getCourseList() {
    Course rapidSpringBootCourse =
        new Course(
            "Rapid Spring Boot Application Development",
            "Spring",
            4,
            "Spring Boot gives all the power of the Spring Framework without all of the"
                + " complexity");
    Course springSecurityDslCourse =
        new Course(
            "Getting Started with Spring Security DSL",
            "Spring",
            5,
            "Learn Spring Security DSL in easy steps");
    Course springCloudKubernetesCourse =
        new Course(
            "Getting Started with Spring Cloud Kubernetes",
            "Spring",
            3,
            "Master Spring Boot application deployment with Kubernetes");
    Course rapidPythonCourse =
        new Course(
            "Getting Started with Python", "Python", 5, "Learn Python concepts in easy steps");
    Course gameDevelopmentWithPython =
        new Course(
            "Game Development with Python",
            "Python",
            2,
            "Learn Python by developing 10 wonderful games");
    Course javaScriptForAll =
        new Course(
            "JavaScript for All",
            "JavaScript",
            4,
            "Learn basic JavaScript syntax that can apply to anywhere");
    Course javaScriptCompleteGuide =
        new Course(
            "JavaScript Complete Guide",
            "JavaScript",
            5,
            "Master JavaScript with Core Concepts and Web Development");

    return Arrays.asList(
        rapidSpringBootCourse,
        springSecurityDslCourse,
        springCloudKubernetesCourse,
        rapidPythonCourse,
        gameDevelopmentWithPython,
        javaScriptForAll,
        javaScriptCompleteGuide);
  }
}
