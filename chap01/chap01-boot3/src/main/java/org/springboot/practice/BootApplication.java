package org.springboot.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(BootApplication.class);
    springApplication.addListeners(new ApplicationStartingEventListener());
    springApplication.run(args);
  }
}
