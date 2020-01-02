package com.sensei.app.service;

import com.sensei.app.service.dto.AssetDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Asset.
 */
public interface AssetService {

    /**
     * Save a asset.
     *
     * @param assetDTO the entity to save
     * @return the persisted entity
     */
    AssetDTO save(AssetDTO assetDTO);

    /**
     * Get all the assets.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AssetDTO> findAll(Pageable pageable);


    /**
     * Get the "id" asset.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AssetDTO> findOne(Long id);

    /**
     * Delete the "id" asset.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the asset corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AssetDTO> search(String query, Pageable pageable);
}
