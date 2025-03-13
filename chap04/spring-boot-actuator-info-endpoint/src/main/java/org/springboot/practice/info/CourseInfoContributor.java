package org.springboot.practice.info;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springboot.practice.model.Course;
import org.springboot.practice.service.CourseService;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CourseInfoContributor implements InfoContributor {

  private final CourseService courseService;

  public void contribute(Info.Builder builder) {
    List<CourseNameRating> courseNameRatingList = new ArrayList<>();
    for (Course course : courseService.getAvailableCourses()) {
      courseNameRatingList.add(
          CourseNameRating.builder().name(course.getName()).rating(course.getRating()).build());
    }
    builder.withDetail("courses", courseNameRatingList);
  }

  @Builder
  @Data
  private static class CourseNameRating {
    String name;
    int rating;
  }
}
