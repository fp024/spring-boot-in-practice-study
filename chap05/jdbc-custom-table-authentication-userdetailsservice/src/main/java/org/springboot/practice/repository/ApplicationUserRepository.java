package org.springboot.practice.repository;

import org.springboot.practice.model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
  ApplicationUser findByUsername(String username);
}
