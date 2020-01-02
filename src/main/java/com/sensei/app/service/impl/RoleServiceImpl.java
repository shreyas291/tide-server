package com.sensei.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensei.app.domain.Role;
import com.sensei.app.domain.RoleAuthority;
import com.sensei.app.repository.RoleAuthorityRepository;
import com.sensei.app.repository.RoleRepository;
import com.sensei.app.service.RoleService;
import com.sensei.app.service.dto.RoleDTO;
import com.sensei.app.service.mapper.RoleMapper;

/**
 * Service Implementation for managing Employee.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

	private final RoleRepository roleRepository;

	private final RoleMapper roleMapper;

	private final RoleAuthorityRepository roleAuthorityRepository;

	public RoleServiceImpl(RoleRepository employeeRepository, RoleMapper roleMapper,
			RoleAuthorityRepository roleAuthorityRepository) {
		this.roleRepository = employeeRepository;
		this.roleMapper = roleMapper;
		this.roleAuthorityRepository = roleAuthorityRepository;
	}

	@Override
	public RoleDTO save(RoleDTO roleDto) {
		List<String> selectedAuthorities = roleDto.getAuthorities();
		Role role = roleMapper.toEntity(roleDto);
		roleRepository.save(role);
		roleDto.setId(role.getId());
		List<RoleAuthority> roleAuthorities = new ArrayList<>();
		for (String authority : selectedAuthorities) {
			RoleAuthority newRoleAuthority = new RoleAuthority();
			newRoleAuthority.setRoleCode(role.getCode());
			newRoleAuthority.setAuthorityName(authority);
			roleAuthorities.add(newRoleAuthority);
		}
		roleAuthorityRepository.deleteAuthoritiesFor(role.getCode());
		roleAuthorityRepository.saveAll(roleAuthorities);
		return roleDto;
	}

	/**
	 * Get all the employees.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<RoleDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Roles");
		Page<Role> result = roleRepository.findAll(pageable);
		return result.map(role -> {
			RoleDTO roleCode = roleMapper.toDto(role);
			roleCode.setAuthorities(roleAuthorityRepository.getAuthoritiesByRoleCode(roleCode.getCode()));
			return roleCode;
		});
	}

	/**
	 * Get one employee by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public RoleDTO findOne(Long id) {
		log.debug("Request to get Role : {}", id);
		Optional<Role> role = roleRepository.findById(id);
		RoleDTO roleDTO = roleMapper.toDto(role.get());
		roleDTO.setAuthorities(roleAuthorityRepository.getAuthoritiesByRoleCode(roleDTO.getCode()));
		return roleDTO;
	}

	/**
	 * Get one Role by code.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public RoleDTO findOneByCode(String roleCode) {
		log.debug("Request to get Role : {}", roleCode);
		Role role = roleRepository.findOneByCode(roleCode);
		RoleDTO roleDTO = roleMapper.toDto(role);
		if (roleDTO != null) {
			roleDTO.setAuthorities(roleAuthorityRepository.getAuthoritiesByRoleCode(roleDTO.getCode()));
		}
		return roleDTO;
	}

	/**
	 * Delete the employee by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(RoleDTO roleDTO) {
		log.debug("Request to delete Role : {}", roleDTO.getId());
		roleAuthorityRepository.deleteAuthoritiesFor(roleDTO.getCode());
		roleRepository.deleteById(roleDTO.getId());
	}
	
	@Override
	public List<RoleDTO> getAllRoles() {
		List<Role> role = roleRepository.findAll();
		return roleMapper.toDto(role);
	}
}
