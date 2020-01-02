package com.sensei.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.sensei.app.service.BranchsetService;
import com.sensei.app.service.dto.BranchsetDTO;
import com.sensei.app.web.rest.errors.BadRequestAlertException;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Branchset.
 */
@RestController
@RequestMapping("/api")
public class BranchsetResource {

    private final Logger log = LoggerFactory.getLogger(BranchsetResource.class);

    private static final String ENTITY_NAME = "branchset";

    private final BranchsetService branchsetService;

    public BranchsetResource(BranchsetService branchsetService) {
        this.branchsetService = branchsetService;
    }

    /**
     * POST  /branchsets : Create a new branchset.
     *
     * @param branchsetDTO the branchsetDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new branchsetDTO, or with status 400 (Bad Request) if the branchset has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/branchsets")
    @Timed
    public ResponseEntity<BranchsetDTO> createBranchset(@RequestBody BranchsetDTO branchsetDTO) throws URISyntaxException {
        log.debug("REST request to save Branchset : {}", branchsetDTO);
        if (branchsetDTO.getId() != null) {
            throw new BadRequestAlertException("A new branchset cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchsetDTO result = branchsetService.save(branchsetDTO);
        return ResponseEntity.created(new URI("/api/branchsets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /branchsets : Updates an existing branchset.
     *
     * @param branchsetDTO the branchsetDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated branchsetDTO,
     * or with status 400 (Bad Request) if the branchsetDTO is not valid,
     * or with status 500 (Internal Server Error) if the branchsetDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/branchsets")
    @Timed
    public ResponseEntity<BranchsetDTO> updateBranchset(@RequestBody BranchsetDTO branchsetDTO) throws URISyntaxException {
        log.debug("REST request to update Branchset : {}", branchsetDTO);
        if (branchsetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BranchsetDTO result = branchsetService.update(branchsetDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, branchsetDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /branchsets : get all the branchsets.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of branchsets in body
     */
    @GetMapping("/branchsets")
    @Timed
    public ResponseEntity<List<BranchsetDTO>> getAllBranchsets(Pageable pageable) {
        log.debug("REST request to get a page of Branchsets");
        Page<BranchsetDTO> page = branchsetService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/branchsets");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /branchsets/:id : get the "id" branchset.
     *
     * @param id the id of the branchsetDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the branchsetDTO, or with status 404 (Not Found)
     */
    @GetMapping("/branchsets/{id}")
    @Timed
    public ResponseEntity<BranchsetDTO> getBranchset(@PathVariable Long id) {
        log.debug("REST request to get Branchset : {}", id);
        Optional<BranchsetDTO> branchsetDTO = branchsetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchsetDTO);
    }

    /**
     * DELETE  /branchsets/:id : delete the "id" branchset.
     *
     * @param id the id of the branchsetDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/branchsets/{id}")
    @Timed
    public ResponseEntity<Void> deleteBranchset(@PathVariable Long id) {
        log.debug("REST request to delete Branchset : {}", id);
        branchsetService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/_getAll/branchsets")
    @Timed
    public ResponseEntity<List<BranchsetDTO>> getAllBranchsets() {
        log.debug("REST request to get a page of Branchsets");
        List<BranchsetDTO> branchsets = branchsetService.findAll();
        return new ResponseEntity<>(branchsets, HttpStatus.OK);
    }

}
