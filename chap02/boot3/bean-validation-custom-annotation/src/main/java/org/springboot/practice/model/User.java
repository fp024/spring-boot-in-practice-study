package org.springboot.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springboot.practice.validators.Password;

@Getter
@ToString
@AllArgsConstructor
public class User {

  private String userName;

  @Password private String password;
}
