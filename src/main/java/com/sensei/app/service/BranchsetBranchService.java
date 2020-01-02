package com.sensei.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.domain.BranchsetBranch;
import com.sensei.app.service.dto.BranchsetBranchDTO;

/**
 * Service Interface for managing BranchsetBranch.
 */
public interface BranchsetBranchService {

    /**
     * Save a branchsetBranch.
     *
     * @param branchsetBranchDTO the entity to save
     * @return the persisted entity
     */
    BranchsetBranchDTO save(BranchsetBranchDTO branchsetBranchDTO);

    /**
     * Get all the branchsetBranches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BranchsetBranchDTO> findAll(Pageable pageable);


    /**
     * Get the "id" branchsetBranch.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BranchsetBranchDTO> findOne(Long id);

    /**
     * Delete the "id" branchsetBranch.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    void createBranchAndBranchsetMapping(Long branchId, Long branchsetId);

	List<BranchsetBranch> findByBranchsetId(Long branchsetId);
	
	void deleteAllMappingFor(Long branchSetId);
}
