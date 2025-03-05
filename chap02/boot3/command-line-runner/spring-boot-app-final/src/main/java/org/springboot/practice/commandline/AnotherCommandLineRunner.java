package org.springboot.practice.commandline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(2)
@Component
public class AnotherCommandLineRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    log.info("AnotherCommandLineRunner executed as a Spring Component");
  }
}
