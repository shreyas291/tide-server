package com.sensei.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.RoleDTO;

/**
 * Service Interface for managing ReferenceCode.
 */
public interface RoleService {

	/**
	 * Save a referenceCode.
	 *
	 * @param role
	 *            the entity to save
	 * @return the persisted entity
	 */
	RoleDTO save(RoleDTO role);

	/**
	 * Get all the referenceCodes.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	Page<RoleDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" referenceCode.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	RoleDTO findOne(Long id);

	RoleDTO findOneByCode(String roleCode);

	void delete(RoleDTO roleDTO);

	List<RoleDTO> getAllRoles();
}
