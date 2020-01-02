package com.sensei.app.service;

import com.sensei.app.service.dto.BranchDTO;
import com.sensei.app.service.dto.BranchsetDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Branchset.
 */
public interface BranchsetService {

    /**
     * Save a branchset.
     *
     * @param branchsetDTO the entity to save
     * @return the persisted entity
     */
    BranchsetDTO save(BranchsetDTO branchsetDTO);

    /**
     * Get all the branchsets.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BranchsetDTO> findAll(Pageable pageable);


    /**
     * Get the "id" branchset.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BranchsetDTO> findOne(Long id);

    /**
     * Delete the "id" branchset.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    BranchsetDTO createBranchSetForBranch(BranchDTO branch);

	List<BranchsetDTO> findAll();

	BranchsetDTO update(BranchsetDTO branchsetDTO);
	
}
