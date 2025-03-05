package org.springboot.practice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class CourseTrackerApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(CourseTrackerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("CourseTrackerApplication CommandLineRunner has executed");
  }

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> {
      log.info(
          "CommandLineRunner executed as a bean definition with " + args.length + " arguments");
      for (int i = 0; i < args.length; i++) {
        log.info("Argument: " + args[i]);
      }
    };
  }
}
