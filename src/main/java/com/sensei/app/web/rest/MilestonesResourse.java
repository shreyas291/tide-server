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
import com.sensei.app.service.MilestonesService;
import com.sensei.app.service.dto.MilestonesDTO;
import com.sensei.app.service.impl.TeachersServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")

public class MilestonesResourse {
	
	private final Logger log = LoggerFactory.getLogger(TeachersServiceImpl.class);

	private static final String ENTITY_NAME ="Milestones";
	
	private final MilestonesService milestonesService;

	public MilestonesResourse(MilestonesService milestonesService) {
		this.milestonesService = milestonesService;
	}


	@PostMapping("/milestones")
	@Timed
	public ResponseEntity<MilestonesDTO> create( @RequestBody MilestonesDTO milestonesDTO)
			throws URISyntaxException {
		log.debug("REST request to save Milestone : {}", milestonesDTO);
		if (milestonesDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new Milestone cannot already have an ID")).body(null);
		}
		MilestonesDTO result = milestonesService.save(milestonesDTO);
		return ResponseEntity.created(new URI("/api/milestones/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	
	@GetMapping("/milestones")
	@Timed
	  public ResponseEntity<List<MilestonesDTO>> getAllMilestones(@ApiParam Pageable pageable) { 
	 Page<MilestonesDTO> page = milestonesService.findall(pageable);
	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/milestones");
	  return new ResponseEntity<List<MilestonesDTO>>(page.getContent(),headers,HttpStatus.OK); 
	  }
	
	@GetMapping("/milestones/{id}")
	@Timed
	  public ResponseEntity<Optional<MilestonesDTO>> getMilestoneById(@PathVariable Long id) throws URISyntaxException { 
	Optional<MilestonesDTO> data = milestonesService.findOne(id);
	return ResponseEntity.created(new URI("/api/milestones/" + data.get().getId()))
			.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, data.get().getId().toString())).body(data);
	  }
	
	@PutMapping("/milestones")
	@Timed
	public ResponseEntity<MilestonesDTO> update( @RequestBody MilestonesDTO milestonesDTO)
			throws URISyntaxException {
		log.debug("REST request to save Milestone : {}", milestonesDTO);
		if (milestonesDTO.getId() == null) {
			return create(milestonesDTO);
		}
		Optional<MilestonesDTO> milestone=milestonesService.findOne(milestonesDTO.getId());
		milestonesDTO.setVersion(milestone.get().getVersion());
		MilestonesDTO result = milestonesService.save(milestonesDTO);
		return ResponseEntity.ok()
		.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
		.body(result);
	}
	@DeleteMapping("/milestones/{id}")
	@Timed
	public ResponseEntity<Void> deleteMilestone(@PathVariable Long id) {
		log.debug("REST request to delete Milestone : {}", id);
		milestonesService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}


