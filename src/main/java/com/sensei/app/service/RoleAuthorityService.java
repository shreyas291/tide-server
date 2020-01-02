package com.sensei.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.domain.RoleAuthority;
import com.sensei.app.service.dto.RoleAuthorityDTO;

/**
 * Service Interface for managing roleAuthority.
 */
public interface RoleAuthorityService {

	/**
	 * Save a roleAuthority.
	 *
	 * @param role
	 *            the entity to save
	 * @return the persisted entity
	 */
	List<RoleAuthorityDTO> save(List<RoleAuthorityDTO> roleAuthorities);

	/**
	 * Get all the roleAuthoritys.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	Page<RoleAuthorityDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" roleAuthority.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	RoleAuthorityDTO findOne(Long id);

	/**
	 * Delete the "id" roleAuthority.
	 *
	 * @param id
	 *            the id of the entity
	 */
	void delete(Long id);

	List<RoleAuthority> findByRoleCode(String roleCode);
}
