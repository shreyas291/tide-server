package com.sensei.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.sensei.app.service.ReferenceCodeService;
import com.sensei.app.service.dto.NameValueDto;
import com.sensei.app.service.dto.ReferenceCodeDTO;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing ReferenceCode.
 */
@RestController
@RequestMapping("/api")
public class ReferenceCodeResource {

	private final Logger log = LoggerFactory.getLogger(ReferenceCodeResource.class);

	private static final String ENTITY_NAME = "referenceCode";

	private final ReferenceCodeService referenceCodeService;

	public ReferenceCodeResource(ReferenceCodeService referenceCodeService) {
		this.referenceCodeService = referenceCodeService;
	}

	/**
	 * POST /reference-codes : Create a new referenceCode.
	 *
	 * @param referenceCodeDTO
	 *            the referenceCodeDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new referenceCodeDTO, or with status 400 (Bad Request) if the
	 *         referenceCode has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/reference-codes")
	@Timed
	public ResponseEntity<ReferenceCodeDTO> createReferenceCode(@Valid @RequestBody ReferenceCodeDTO referenceCodeDTO)
			throws URISyntaxException {
		log.debug("REST request to save ReferenceCode : {}", referenceCodeDTO);
		if (referenceCodeDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new referenceCode cannot already have an ID")).body(null);
		}
		ReferenceCodeDTO result = referenceCodeService.save(referenceCodeDTO);
		return ResponseEntity.created(new URI("/api/reference-codes/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /reference-codes : Updates an existing referenceCode.
	 *
	 * @param referenceCodeDTO
	 *            the referenceCodeDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         referenceCodeDTO, or with status 400 (Bad Request) if the
	 *         referenceCodeDTO is not valid, or with status 500 (Internal
	 *         Server Error) if the referenceCodeDTO couldnt be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/reference-codes")
	@Timed
	public ResponseEntity<ReferenceCodeDTO> updateReferenceCode(@Valid @RequestBody ReferenceCodeDTO referenceCodeDTO)
			throws URISyntaxException {
		log.debug("REST request to update ReferenceCode : {}", referenceCodeDTO);
		if (referenceCodeDTO.getId() == null) {
			return createReferenceCode(referenceCodeDTO);
		}
		ReferenceCodeDTO result = referenceCodeService.save(referenceCodeDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, referenceCodeDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /reference-codes : get all the referenceCodes.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         referenceCodes in body
	 */
	@GetMapping("/reference-codes")
	@Timed
	public ResponseEntity<List<ReferenceCodeDTO>> getAllReferenceCodes(@ApiParam Pageable pageable) {
		log.debug("REST request to get a page of ReferenceCodes");
		Page<ReferenceCodeDTO> page = referenceCodeService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reference-codes");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /reference-codes/:id : get the "id" referenceCode.
	 *
	 * @param id
	 *            the id of the referenceCodeDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         referenceCodeDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/reference-codes/{id}")
	@Timed
	public ResponseEntity<ReferenceCodeDTO> getReferenceCode(@PathVariable Long id) {
		log.debug("REST request to get ReferenceCode : {}", id);
		ReferenceCodeDTO referenceCodeDTO = referenceCodeService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(referenceCodeDTO));
	}

	/**
	 * DELETE /reference-codes/:id : delete the "id" referenceCode.
	 *
	 * @param id
	 *            the id of the referenceCodeDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/reference-codes/{id}")
	@Timed
	public ResponseEntity<Void> deleteReferenceCode(@PathVariable Long id) {
		log.debug("REST request to delete ReferenceCode : {}", id);
		referenceCodeService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}


	/**
	 * GET _refs/referencecodes/classifiers -> get all the classifiers.
	 *
	 */
	@GetMapping("/_refs/reference-codes/classifiers")
	@Timed
	public List<NameValueDto> getClassifiers() {
		List<NameValueDto> results = new ArrayList<NameValueDto>();
		List<String> classifiers = referenceCodeService.findDistinctClassifiers();
		for (String classifier : classifiers) {
			results.add(new NameValueDto(classifier, classifier));
		}
		return results;
	}

	/**
	 * GET _refs/referencecodes/codes/{classifier} -> get all the classifiers.
	 *
	 */
	@GetMapping("/_refs/reference-codes/codes/{classifier}")
	@Timed
	public List<NameValueDto> getReferenceCodes(@PathVariable String classifier,
			@RequestParam(value = "status", required = false, defaultValue = "0") Integer status) {
		List<NameValueDto> results = new ArrayList<NameValueDto>();
		List<ReferenceCodeDTO> refCodes = referenceCodeService.find(classifier, status);
		for (ReferenceCodeDTO refCode : refCodes) {
			results.add(new NameValueDto(refCode.getCode(), refCode.getName()));
		}
		return results;
	}

	/**
	 * GET _refs/referencecodes/parentcodes/{classifier} -> get all the
	 * classifiers.
	 *
	 */
	@GetMapping("/_refs/reference-codes/childcodes/{classifier}/{parentReferenceCode}")
	@Timed
	public List<NameValueDto> getChildReferenceCodes(@PathVariable String classifier,
			@PathVariable String parentReferenceCode,
			@RequestParam(value = "status", required = false, defaultValue = "0") Integer status) {
		List<NameValueDto> results = new ArrayList<NameValueDto>();
		List<ReferenceCodeDTO> refCodes = referenceCodeService.findChildReferenceCodes(classifier, parentReferenceCode,
				status);
		for (ReferenceCodeDTO refCode : refCodes) {
			results.add(new NameValueDto(refCode.getCode(), refCode.getName()));
		}
		return results;
	}

	/**
	 * GET _refs/referencecodes/parentcodes/{classifier} -> get all the
	 * classifiers.
	 *
	 */
	@GetMapping("/_refs/reference-codes/parentcodes/{classifier}")
	@Timed
	public List<NameValueDto> getParentReferenceCodes(@PathVariable String classifier,
			@RequestParam(value = "status", required = false, defaultValue = "0") Integer status) {
		List<NameValueDto> results = new ArrayList<NameValueDto>();
		List<ReferenceCodeDTO> refCodes = referenceCodeService.findParentReferenceCodes(classifier, status);
		for (ReferenceCodeDTO refCode : refCodes) {
			results.add(new NameValueDto(refCode.getCode(), refCode.getName()));
		}
		return results;
	}

	/**
	 * GET _refs/referencecodes/codes/{classifier} -> get all the classifiers.
	 *
	 */
	@GetMapping("/_refs/reference-codes/all/codes/{classifiers}")
	@Timed
	public Map<String, List<NameValueDto>> getReferenceCode(@PathVariable String[] classifiers,
			@RequestParam(value = "status", required = false, defaultValue = "0") Integer status) {
		Map<String, List<NameValueDto>> results = new HashMap<String, List<NameValueDto>>();
		for (String classifier : classifiers) {
			List<NameValueDto> classifierRefCodes = new ArrayList<NameValueDto>();
			List<ReferenceCodeDTO> refCodes = referenceCodeService.find(classifier, status);
			for (ReferenceCodeDTO refCode : refCodes) {
				classifierRefCodes.add(new NameValueDto(refCode.getCode(), refCode.getName()));
			}
			results.put(classifier, classifierRefCodes);
		}
		return results;
	}

	/**
	 * DESABLE /referencecodes/:id -> DISABLE the "id" referenceCode.
	 */
	@GetMapping("/reference-codes/{id}/{status}")
	@Timed
	public ResponseEntity<String> disable(@PathVariable Long id, @PathVariable Integer status) {
		log.debug("REST request to disable ReferenceCode : {}", id);
		referenceCodeService.disable(id, status);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
