package org.springboot.practice.model;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Data;

@Entity
@Table(name = "CT_USERS")
@Data
public class ApplicationUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;

  @Column(name = "USERNAME", nullable = false, unique = true)
  private String username;

  private String email;
  private String password;
  private boolean verified;
  private boolean locked;

  @Column(name = "ACC_CRED_EXPIRED")
  private boolean accountCredentialsExpired;

  @OneToMany(mappedBy = "user")
  private Set<Authority> authorities;
}
