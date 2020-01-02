package com.sensei.app.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sensei.app.domain.Authority;
import com.sensei.app.repository.AuthorityRepository;
import com.sensei.app.service.AuthorityService;
import com.sensei.app.service.dto.AuthorityDTO;

/**
 * Created by prashanth on 16/5/17.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

	private AuthorityRepository authorityRepository;

	public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
		super();
		this.authorityRepository = authorityRepository;
	}

	@Override
	public List<AuthorityDTO> findAllAuthorities() {
		Reflections reflections = new Reflections("com.sensei.app.web.rest");

		Set<Class<? extends Object>> allClasses = reflections.getTypesAnnotatedWith(RequestMapping.class);

		List<AuthorityDTO> authorityDTOS = new ArrayList<>();
		Set<String> authorities = new HashSet<>();

		for (Class klass : allClasses) {
			final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));

			for (Method method : allMethods) {
				if (method.isAnnotationPresent(PreAuthorize.class)) {
					PreAuthorize annotation = method.getAnnotation(PreAuthorize.class);
					String[] splitOne = annotation.value().split("hasAuthority");
					authorities.add(StringUtils.substringBetween(splitOne[1], "('", "')"));
				}
			}
		}

		for (String authority : authorities) {
			AuthorityDTO authorityDTO = new AuthorityDTO();
			authorityDTO.setAuthorityName(authority);
			authorityDTOS.add(authorityDTO);
		}

		return authorityDTOS;
	}

	@Override
	public Set<AuthorityDTO> findAuthorities() {
		Set<AuthorityDTO> authorities = new HashSet<>();
		List<Authority> authoritys = authorityRepository.findAll();
		for (Authority authority : authoritys) {
			AuthorityDTO authorityDTO = new AuthorityDTO();
			authorityDTO.setAuthorityName(authority.getName());
			authorities.add(authorityDTO);
		}
		return authorities;
	}
}
