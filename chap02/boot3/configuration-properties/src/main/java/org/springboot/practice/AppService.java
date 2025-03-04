package org.springboot.practice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springboot.practice.configurationproperties.AppProperties;
import org.springframework.stereotype.Service;

@Getter
@RequiredArgsConstructor
@Service
public class AppService {

  private final AppProperties appProperties;
}
