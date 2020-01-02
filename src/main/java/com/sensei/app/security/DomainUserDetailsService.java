package com.sensei.app.security;

import com.sensei.app.domain.RoleAuthority;
import com.sensei.app.domain.User;
import com.sensei.app.repository.RoleAuthorityRepository;
import com.sensei.app.repository.UserRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;
    
    private final RoleAuthorityRepository roleAuthorityRepository;

    public DomainUserDetailsService(UserRepository userRepository, RoleAuthorityRepository roleAuthorityRepository) {
        this.userRepository = userRepository;
        this.roleAuthorityRepository = roleAuthorityRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        if (new EmailValidator().isValid(login, null)) {
            Optional<User> userByEmailFromDatabase = userRepository.findOneByLogin(login);
            return userByEmailFromDatabase.map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<User> userByLoginFromDatabase = userRepository.findOneByLogin(lowercaseLogin);
        return userByLoginFromDatabase.map(user -> createSpringSecurityUser(lowercaseLogin, user))
            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));

    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.getActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findByRoleCode(user.getRole());
        
        List<GrantedAuthority> grantedAuthorities = roleAuthorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
            .collect(Collectors.toList());
        
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
            user.getPassword(),
            grantedAuthorities);
    }
}
