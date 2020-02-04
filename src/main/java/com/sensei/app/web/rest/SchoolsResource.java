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
import com.sensei.app.service.SchoolsService;
import com.sensei.app.service.dto.SchoolsDTO;
import com.sensei.app.service.impl.SchoolsServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class SchoolsResource {

	private final Logger log = LoggerFactory.getLogger(SchoolsServiceImpl.class);

	private static final String ENTITY_NAME = "Schools";

	private final SchoolsService schoolsService;

	public SchoolsResource(SchoolsService schoolsService) {
		this.schoolsService = schoolsService;
	}

	@PostMapping("/schools")
	@Timed
	public ResponseEntity<SchoolsDTO> create(@RequestBody SchoolsDTO schoolsDTO) throws URISyntaxException {
		log.debug("REST request to save School : {}", schoolsDTO);
		if (schoolsDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new school cannot already have an ID"))
					.body(null);
		}
		SchoolsDTO result = schoolsService.save(schoolsDTO);
		return ResponseEntity.created(new URI("/api/schools/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	@GetMapping("/schools")
	@Timed
	public ResponseEntity<List<SchoolsDTO>> getAllSchools(@ApiParam Pageable pageable) {
		Page<SchoolsDTO> page = schoolsService.findall(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/schools");
		return new ResponseEntity<List<SchoolsDTO>>(page.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping("/schools/{id}")
	@Timed
	public ResponseEntity<Optional<SchoolsDTO>> getSchoolById(@PathVariable Long id) throws URISyntaxException {
		Optional<SchoolsDTO> data = schoolsService.findOne(id);
		return ResponseEntity.created(new URI("/api/schools/" + data.get().getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, data.get().getId().toString())).body(data);
	}

	@PutMapping("/schools")
	@Timed
	public ResponseEntity<SchoolsDTO> update(@RequestBody SchoolsDTO schoolsDTO) throws URISyntaxException {
		log.debug("REST request to save School : {}", schoolsDTO);
		if (schoolsDTO.getId() == null) {
			return create(schoolsDTO);
		}

		Optional<SchoolsDTO> schools = schoolsService.findOne(schoolsDTO.getId());
		schoolsDTO.setVersion(schools.get().getVersion());
		SchoolsDTO result = schoolsService.save(schoolsDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	@DeleteMapping("/schools/{id}")
	@Timed
	public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
		log.debug("REST request to delete School : {}", id);
		schoolsService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
	
//	@GetMapping("/schools/getGeoLocation")
//	@Timed
//	public ResponseEntity<String> getGeoLocation() {
//	
//		String response = schoolsService.getGeoLocation();
//		return new ResponseEntity<String>(response, HttpStatus.OK);
//	}
}
