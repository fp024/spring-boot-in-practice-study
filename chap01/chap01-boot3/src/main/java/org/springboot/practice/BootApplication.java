package org.springboot.practice;

import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BootApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
    System.out.println(
        "Application Ready Event generated at " + new Date(applicationReadyEvent.getTimestamp()));
  }
}
