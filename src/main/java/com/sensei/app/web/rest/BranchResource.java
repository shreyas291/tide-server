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
import com.sensei.app.domain.User;
import com.sensei.app.security.AuthoritiesConstants;
import com.sensei.app.security.SecurityUtils;
import com.sensei.app.service.BranchService;
import com.sensei.app.service.UserService;
import com.sensei.app.service.dto.BranchDTO;
import com.sensei.app.web.rest.errors.BadRequestAlertException;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Branch.
 */
@RestController
@RequestMapping("/api")
public class BranchResource {

    private final Logger log = LoggerFactory.getLogger(BranchResource.class);

    private static final String ENTITY_NAME = "branch";

    private final BranchService branchService;
    
    private final UserService userService;
    
    public BranchResource(BranchService branchService, UserService userService) {
        this.branchService = branchService;
        this.userService = userService;
    }

    /**
     * POST  /branches : Create a new branch.
     *
     * @param branchDTO the branchDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new branchDTO, or with status 400 (Bad Request) if the branch has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/branches")
    @Timed
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) throws URISyntaxException {
        log.debug("REST request to save Branch : {}", branchDTO);
        if (branchDTO.getId() != null) {
            throw new BadRequestAlertException("A new branch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchDTO result = branchService.save(branchDTO);
        return ResponseEntity.created(new URI("/api/branches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /branches : Updates an existing branch.
     *
     * @param branchDTO the branchDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated branchDTO,
     * or with status 400 (Bad Request) if the branchDTO is not valid,
     * or with status 500 (Internal Server Error) if the branchDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/branches")
    @Timed
    public ResponseEntity<BranchDTO> updateBranch(@RequestBody BranchDTO branchDTO) throws URISyntaxException {
        log.debug("REST request to update Branch : {}", branchDTO);
        if (branchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BranchDTO result = branchService.update(branchDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, branchDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /branches : get all the branches.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of branches in body
     */
    @GetMapping("/branches")
    @Timed
    public ResponseEntity<List<BranchDTO>> getAllBranches(Pageable pageable) {
    	 log.debug("REST request to get a page of Branches");
         Page<BranchDTO> page = branchService.findAll(pageable);
         HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/branches");
         return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /branches/:id : get the "id" branch.
     *
     * @param id the id of the branchDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the branchDTO, or with status 404 (Not Found)
     */
    @GetMapping("/branches/{id}")
    @Timed
    public ResponseEntity<BranchDTO> getBranch(@PathVariable Long id) {
        log.debug("REST request to get Branch : {}", id);
        Optional<BranchDTO> branchDTO = branchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchDTO);
    }

    /**
     * DELETE  /branches/:id : delete the "id" branch.
     *
     * @param id the id of the branchDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/branches/{id}")
    @Timed
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        log.debug("REST request to delete Branch : {}", id);
        branchService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/branches/user")
    @Timed
    public ResponseEntity<List<BranchDTO>> getAllBranchesForUser() {
    	log.debug("REST request to get a page of Branches");
        String userLogin = SecurityUtils.getCurrentUserLoginId();
		Optional<User> user = userService.findOneByLogin(userLogin);
		List<BranchDTO> branchesDto = null;
		if(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
			branchesDto = branchService.findAll();
		}else {
			branchesDto = branchService.getAllBranchesByBranchsetId(new Long(1)); //TODO Needs to change the branch set id from user
		}
		 return ResponseEntity.ok().body(branchesDto);
    }

    /*@GetMapping("/branches/role")
    @Timed
    public ResponseEntity<List<BranchDTO>> getBranchesAsPerRoles() {
        log.debug("REST request to get all Branches");
        List<BranchDTO> branchesDto = null;
        if(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
			branchesDto = branchService.findAll();
		}else {
			branchesDto = branchService.getAllBranchesByBranchsetId(user.get().getBranchsetId());
		}
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }*/
    
}
