package org.springboot.practice.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springboot.practice.service.CourseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseTrackerMetricsConfiguration {

  @Bean
  Counter createCourseCounter(MeterRegistry meterRegistry) {
    return Counter.builder("api.courses.created.count")
        .description("Total number of courses created")
        .register(meterRegistry);
  }

  @Bean
  Gauge createCoursesGauge(MeterRegistry meterRegistry, CourseService courseService) {
    return Gauge.builder("api.courses.created.gauge", courseService::count)
        .description("Total courses created")
        .register(meterRegistry);
  }

  @Bean
  Timer createCoursesTimer(MeterRegistry meterRegistry) {
    return Timer.builder("api.courses.creation.time")
        .description("Course creation time")
        /*
        .sla(Duration.ofMillis(10))
        .minimumExpectedValue(Duration.ofMillis((1)))
        .maximumExpectedValue(Duration.ofMillis(10))
        .publishPercentiles(0.5, 0.95)
        .publishPercentileHistogram()
        */
        .register(meterRegistry);
  }

  @Bean
  DistributionSummary createDistributionSummary(MeterRegistry meterRegistry) {
    return DistributionSummary.builder("api.courses.rating.distribution.summary")
        .description("Rating distribution summary")
        .register(meterRegistry);
  }
}
