package org.springboot.practice.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CT_AUTHORITIES")
@Getter
@Setter
@NoArgsConstructor
public class Authority {
  @EmbeddedId private AuthorityId id;

  @MapsId("username") // EmbeddedId의 username 필드와 FK를 연결
  @ManyToOne
  @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
  private ApplicationUser user;
}
