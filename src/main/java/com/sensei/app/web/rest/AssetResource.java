package com.sensei.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.sensei.app.service.AssetService;
import com.sensei.app.web.rest.errors.BadRequestAlertException;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;
import com.sensei.app.service.dto.AssetDTO;
import com.sensei.app.service.dto.AssetCriteria;
import com.sensei.app.service.AssetQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Asset.
 */
@RestController
@RequestMapping("/api")
public class AssetResource {

    private final Logger log = LoggerFactory.getLogger(AssetResource.class);

    private static final String ENTITY_NAME = "asset";

    private final AssetService assetService;

    private final AssetQueryService assetQueryService;

    public AssetResource(AssetService assetService, AssetQueryService assetQueryService) {
        this.assetService = assetService;
        this.assetQueryService = assetQueryService;
    }

    /**
     * POST  /assets : Create a new asset.
     *
     * @param assetDTO the assetDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetDTO, or with status 400 (Bad Request) if the asset has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/assets")
    @Timed
    public ResponseEntity<AssetDTO> createAsset(@Valid @RequestBody AssetDTO assetDTO) throws URISyntaxException {
        log.debug("REST request to save Asset : {}", assetDTO);
        if (assetDTO.getId() != null) {
            throw new BadRequestAlertException("A new asset cannot already have an ID", ENTITY_NAME, "idexists");
        }        
        AssetDTO result = assetService.save(assetDTO);
        return ResponseEntity.created(new URI("/api/assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /assets : Updates an existing asset.
     *
     * @param assetDTO the assetDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetDTO,
     * or with status 400 (Bad Request) if the assetDTO is not valid,
     * or with status 500 (Internal Server Error) if the assetDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/assets")
    @Timed
    public ResponseEntity<AssetDTO> updateAsset(@Valid @RequestBody AssetDTO assetDTO) throws URISyntaxException {
        log.debug("REST request to update Asset : {}", assetDTO);
        if (assetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }        
        AssetDTO result = assetService.save(assetDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /assets : get all the assets.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of assets in body
     */
    @GetMapping("/assets")
    @Timed
    public ResponseEntity<List<AssetDTO>> getAllAssets(AssetCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Assets by criteria: {}", criteria);
        Page<AssetDTO> page = assetQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/assets");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /assets/:id : get the "id" asset.
     *
     * @param id the id of the assetDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetDTO, or with status 404 (Not Found)
     */
    @GetMapping("/assets/{id}")
    @Timed
    public ResponseEntity<AssetDTO> getAsset(@PathVariable Long id) {
        log.debug("REST request to get Asset : {}", id);
        Optional<AssetDTO> assetDTO = assetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assetDTO);
    }

    /**
     * DELETE  /assets/:id : delete the "id" asset.
     *
     * @param id the id of the assetDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/assets/{id}")
    @Timed
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        log.debug("REST request to delete Asset : {}", id);
        assetService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/assets?query=:query : search for the asset corresponding
     * to the query.
     *
     * @param query the query of the asset search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/assets")
    @Timed
    public ResponseEntity<List<AssetDTO>> searchAssets(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Assets for query {}", query);
        Page<AssetDTO> page = assetService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/assets");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
