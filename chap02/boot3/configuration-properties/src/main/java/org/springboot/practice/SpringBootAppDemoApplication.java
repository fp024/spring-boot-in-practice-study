package org.springboot.practice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@ConfigurationPropertiesScan(basePackages = "org.springboot.practice.configurationproperties")
@SpringBootApplication
public class SpringBootAppDemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext =
        SpringApplication.run(SpringBootAppDemoApplication.class, args);

    AppService appService = applicationContext.getBean(AppService.class);
    log.info(appService.getAppProperties().toString());
  }
}
