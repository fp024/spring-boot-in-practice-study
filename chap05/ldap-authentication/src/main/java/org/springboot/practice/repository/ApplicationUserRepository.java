package org.springboot.practice.repository;

import org.springboot.practice.model.ApplicationUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
  ApplicationUser findByUsername(String username);

  @Query(
      """
      SELECT u
      FROM ApplicationUser u
      LEFT JOIN FETCH u.authorities a
      WHERE u.username = :username
      """)
  ApplicationUser findByUsernameWithAuthorities(@Param("username") String username);
}
