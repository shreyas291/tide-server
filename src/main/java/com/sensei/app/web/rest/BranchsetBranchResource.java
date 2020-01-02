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
import com.sensei.app.service.BranchsetBranchService;
import com.sensei.app.service.dto.BranchsetBranchDTO;
import com.sensei.app.web.rest.errors.BadRequestAlertException;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing BranchsetBranch.
 */
@RestController
@RequestMapping("/api")
public class BranchsetBranchResource {

    private final Logger log = LoggerFactory.getLogger(BranchsetBranchResource.class);

    private static final String ENTITY_NAME = "branchsetBranch";

    private final BranchsetBranchService branchsetBranchService;

    public BranchsetBranchResource(BranchsetBranchService branchsetBranchService) {
        this.branchsetBranchService = branchsetBranchService;
    }

    /**
     * POST  /branchset-branches : Create a new branchsetBranch.
     *
     * @param branchsetBranchDTO the branchsetBranchDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new branchsetBranchDTO, or with status 400 (Bad Request) if the branchsetBranch has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/branchset-branches")
    @Timed
    public ResponseEntity<BranchsetBranchDTO> createBranchsetBranch(@RequestBody BranchsetBranchDTO branchsetBranchDTO) throws URISyntaxException {
        log.debug("REST request to save BranchsetBranch : {}", branchsetBranchDTO);
        if (branchsetBranchDTO.getId() != null) {
            throw new BadRequestAlertException("A new branchsetBranch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchsetBranchDTO result = branchsetBranchService.save(branchsetBranchDTO);
        return ResponseEntity.created(new URI("/api/branchset-branches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /branchset-branches : Updates an existing branchsetBranch.
     *
     * @param branchsetBranchDTO the branchsetBranchDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated branchsetBranchDTO,
     * or with status 400 (Bad Request) if the branchsetBranchDTO is not valid,
     * or with status 500 (Internal Server Error) if the branchsetBranchDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/branchset-branches")
    @Timed
    public ResponseEntity<BranchsetBranchDTO> updateBranchsetBranch(@RequestBody BranchsetBranchDTO branchsetBranchDTO) throws URISyntaxException {
        log.debug("REST request to update BranchsetBranch : {}", branchsetBranchDTO);
        if (branchsetBranchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BranchsetBranchDTO result = branchsetBranchService.save(branchsetBranchDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, branchsetBranchDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /branchset-branches : get all the branchsetBranches.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of branchsetBranches in body
     */
    @GetMapping("/branchset-branches")
    @Timed
    public ResponseEntity<List<BranchsetBranchDTO>> getAllBranchsetBranches(Pageable pageable) {
        log.debug("REST request to get a page of BranchsetBranches");
        Page<BranchsetBranchDTO> page = branchsetBranchService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/branchset-branches");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /branchset-branches/:id : get the "id" branchsetBranch.
     *
     * @param id the id of the branchsetBranchDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the branchsetBranchDTO, or with status 404 (Not Found)
     */
    @GetMapping("/branchset-branches/{id}")
    @Timed
    public ResponseEntity<BranchsetBranchDTO> getBranchsetBranch(@PathVariable Long id) {
        log.debug("REST request to get BranchsetBranch : {}", id);
        Optional<BranchsetBranchDTO> branchsetBranchDTO = branchsetBranchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchsetBranchDTO);
    }

    /**
     * DELETE  /branchset-branches/:id : delete the "id" branchsetBranch.
     *
     * @param id the id of the branchsetBranchDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/branchset-branches/{id}")
    @Timed
    public ResponseEntity<Void> deleteBranchsetBranch(@PathVariable Long id) {
        log.debug("REST request to delete BranchsetBranch : {}", id);
        branchsetBranchService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
