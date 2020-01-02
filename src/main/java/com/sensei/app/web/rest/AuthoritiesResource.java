package com.sensei.app.web.rest;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.sensei.app.service.AuthorityService;
import com.sensei.app.service.dto.AuthorityDTO;

/**
 * Created by prashanth on 16/5/17.
 */
@RequestMapping("/api")
@RestController
public class AuthoritiesResource {
    private static Logger logger = LoggerFactory.getLogger(AuthoritiesResource.class);
    private final AuthorityService authorityService;

    public AuthoritiesResource(AuthorityService authorityService){
        this.authorityService = authorityService;
    }

    @GetMapping("/authorities")
    @Timed
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Set<AuthorityDTO>> getAllAuthorities(){
        logger.info("Request to get all authorities");
        return new ResponseEntity<Set<AuthorityDTO>>(authorityService.findAuthorities(), new HttpHeaders(), HttpStatus.OK);
    }
    
  }
