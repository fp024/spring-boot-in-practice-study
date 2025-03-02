package org.springboot.practice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@RequiredArgsConstructor
@Configuration
@PropertySource("classpath:dbConfig.properties")
public class DbConfiguration {

  private final Environment env;

  @Override
  public String toString() {
    return "User: " + env.getProperty("user") + ", Password: " + env.getProperty("password");
  }
}
