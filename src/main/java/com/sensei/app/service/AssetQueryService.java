package com.sensei.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.sensei.app.domain.Asset;
import com.sensei.app.domain.*; // for static metamodels
import com.sensei.app.repository.AssetRepository;
import com.sensei.app.repository.search.AssetSearchRepository;
import com.sensei.app.service.dto.AssetCriteria;

import com.sensei.app.service.dto.AssetDTO;
import com.sensei.app.service.mapper.AssetMapper;

/**
 * Service for executing complex queries for Asset entities in the database.
 * The main input is a {@link AssetCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AssetDTO} or a {@link Page} of {@link AssetDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AssetQueryService extends QueryService<Asset> {

    private final Logger log = LoggerFactory.getLogger(AssetQueryService.class);

    private final AssetRepository assetRepository;

    private final AssetMapper assetMapper;

    private final AssetSearchRepository assetSearchRepository;

    public AssetQueryService(AssetRepository assetRepository, AssetMapper assetMapper, AssetSearchRepository assetSearchRepository) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
        this.assetSearchRepository = assetSearchRepository;
    }

    /**
     * Return a {@link List} of {@link AssetDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AssetDTO> findByCriteria(AssetCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Asset> specification = createSpecification(criteria);
        return assetMapper.toDto(assetRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AssetDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AssetDTO> findByCriteria(AssetCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Asset> specification = createSpecification(criteria);
        return assetRepository.findAll(specification, page)
            .map(assetMapper::toDto);
    }

    /**
     * Function to convert AssetCriteria to a {@link Specification}
     */
    private Specification<Asset> createSpecification(AssetCriteria criteria) {
        Specification<Asset> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Asset_.id));
            }
            if (criteria.getAssetType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssetType(), Asset_.assetType));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), Asset_.price));
            }
            if (criteria.getProcurementDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProcurementDate(), Asset_.procurementDate));
            }
            if (criteria.getAssetId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssetId(), Asset_.assetId));
            }
            if (criteria.getValidityAvailable() != null) {
                specification = specification.and(buildSpecification(criteria.getValidityAvailable(), Asset_.validityAvailable));
            }
        }
        return specification;
    }

}
