package com.sensei.app.service.impl;

import com.sensei.app.service.AssetService;
import com.sensei.app.domain.Asset;
import com.sensei.app.repository.AssetRepository;
import com.sensei.app.repository.search.AssetSearchRepository;
import com.sensei.app.service.dto.AssetDTO;
import com.sensei.app.service.mapper.AssetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Asset.
 */
@Service
@Transactional
public class AssetServiceImpl implements AssetService {

    private final Logger log = LoggerFactory.getLogger(AssetServiceImpl.class);

    private final AssetRepository assetRepository;

    private final AssetMapper assetMapper;

    private final AssetSearchRepository assetSearchRepository;

    public AssetServiceImpl(AssetRepository assetRepository, AssetMapper assetMapper, AssetSearchRepository assetSearchRepository) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
        this.assetSearchRepository = assetSearchRepository;
    }

    /**
     * Save a asset.
     *
     * @param assetDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AssetDTO save(AssetDTO assetDTO) {
        log.debug("Request to save Asset : {}", assetDTO);
        Asset asset = assetMapper.toEntity(assetDTO);
        asset = assetRepository.save(asset);
        AssetDTO result = assetMapper.toDto(asset);
        // assetSearchRepository.save(asset);
        return result;
    }

    /**
     * Get all the assets.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AssetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Assets");
        return assetRepository.findAll(pageable)
            .map(assetMapper::toDto);
    }


    /**
     * Get one asset by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AssetDTO> findOne(Long id) {
        log.debug("Request to get Asset : {}", id);
        return assetRepository.findById(id)
            .map(assetMapper::toDto);
    }

    /**
     * Delete the asset by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Asset : {}", id);
        assetRepository.deleteById(id);
        assetSearchRepository.deleteById(id);
    }

    /**
     * Search for the asset corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AssetDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Assets for query {}", query);
        return assetSearchRepository.search(queryStringQuery(query), pageable)
            .map(assetMapper::toDto);
    }
}
