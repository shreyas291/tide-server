package com.sensei.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sensei.app.service.dto.ReferenceCodeDTO;

/**
 * Service Interface for managing ReferenceCode.
 */
public interface ReferenceCodeService {

    /**
     * Save a referenceCode.
     *
     * @param referenceCodeDTO the entity to save
     * @return the persisted entity
     */
    ReferenceCodeDTO save(ReferenceCodeDTO referenceCodeDTO);

    /**
     *  Get all the referenceCodes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ReferenceCodeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" referenceCode.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ReferenceCodeDTO findOne(Long id);

    /**
     *  Delete the "id" referenceCode.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the referenceCode corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */

	List<String> findDistinctClassifiers();

	List<ReferenceCodeDTO> find(String classifier, Integer status);

	List<ReferenceCodeDTO> findChildReferenceCodes(String classifier, String parentReferenceCode, Integer status);

	List<ReferenceCodeDTO> findParentReferenceCodes(String classifier, Integer status);

	void disable(Long id, Integer status);
}
