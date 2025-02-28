package org.springboot.practice;

import java.util.Date;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartingEventListener
    implements ApplicationListener<ApplicationStartingEvent> {

  @Override
  public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {

    System.out.println(
        "Application Starting Event logged at "
            + new Date(applicationStartingEvent.getTimestamp()));
  }
}
