package org.springboot.practice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AuthorityId implements Serializable {
  @Column(name = "USERNAME", length = 255, nullable = false)
  private String username;

  @Column(name = "AUTHORITY", length = 50, nullable = false)
  private String authority;
}
