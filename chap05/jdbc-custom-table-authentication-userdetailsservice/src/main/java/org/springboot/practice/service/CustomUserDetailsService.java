package org.springboot.practice.service;

import lombok.RequiredArgsConstructor;
import org.springboot.practice.model.ApplicationUser;
import org.springboot.practice.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final ApplicationUserRepository applicationUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
    if (applicationUser == null) {
      throw new UsernameNotFoundException("No user with " + username + " exists in the system");
    }
    return User.builder()
        .username(applicationUser.getUsername())
        .password(applicationUser.getPassword())
        .disabled(!applicationUser.isVerified())
        .accountExpired(applicationUser.isAccountCredentialsExpired())
        .accountLocked(applicationUser.isLocked())
        // .roles("USER")
        .authorities("ROLE_USER")
        .build();
  }
}
