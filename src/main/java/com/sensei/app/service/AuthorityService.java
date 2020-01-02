package com.sensei.app.service;

import com.sensei.app.service.dto.AuthorityDTO;

import java.util.List;
import java.util.Set;

/**
 * Created by prashanth on 16/5/17.
 */
public interface AuthorityService {
    List<AuthorityDTO> findAllAuthorities();

    Set<AuthorityDTO> findAuthorities();
}
