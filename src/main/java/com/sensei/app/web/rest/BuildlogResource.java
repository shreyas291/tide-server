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
import com.sensei.app.service.BuildlogService;
import com.sensei.app.service.dto.BuildlogDTO;
import com.sensei.app.service.dto.SchoolsDTO;
import com.sensei.app.service.impl.BuildlogServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")

public class BuildlogResource {
	
	
	private final Logger log = LoggerFactory.getLogger(BuildlogServiceImpl.class);
	
	private BuildlogService buildlogService;
	
	private final String ENTITY_NAME="Buildlog";
	
	
	
	public BuildlogResource(BuildlogService buildlogService) {
		this.buildlogService = buildlogService;
	}



	@PostMapping("/buildlog")
	@Timed
	public ResponseEntity<BuildlogDTO> create( @RequestBody BuildlogDTO buildlogDTO)
			throws URISyntaxException {
		log.debug("REST request to save ReferenceCode : {}", buildlogDTO);
		if (buildlogDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new Buildlog cannot already have an ID")).body(null);
		}
		BuildlogDTO result = buildlogService.save(buildlogDTO);
		return ResponseEntity.created(new URI("/api/buildlog/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}
	@GetMapping("/buildlog")
	@Timed
	  public ResponseEntity<List<BuildlogDTO>> getAllBuildlog(@ApiParam Pageable pageable) { 
	 Page<BuildlogDTO> page = buildlogService.findall(pageable);
	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/buildlog");
	  return new ResponseEntity<List<BuildlogDTO>>(page.getContent(),headers,HttpStatus.OK); 
	  }
	
	@GetMapping("/buildlog/{id}")
	@Timed
	  public ResponseEntity<Optional<BuildlogDTO>> getByBuildlogId(@PathVariable Long id) throws URISyntaxException { 
	 Optional<BuildlogDTO> data = buildlogService.findOne(id);
	 return ResponseEntity.created(new URI("/api/buildlog/" + data.get().getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, data.get().getId().toString())).body(data);
	  }
	
	@PutMapping("/buildlog")
	@Timed
	public ResponseEntity<BuildlogDTO> update( @RequestBody BuildlogDTO buildlogDTO)
			throws URISyntaxException {
		log.debug("REST request to save ReferenceCode : {}", buildlogDTO);
		if (buildlogDTO.getId() == null) {
			return create(buildlogDTO);
		}
		Optional<BuildlogDTO> buildlog=buildlogService.findOne(buildlogDTO.getId());
		buildlogDTO.setVersion(buildlog.get().getVersion());
		BuildlogDTO result = buildlogService.save(buildlogDTO);
		return ResponseEntity.ok()
		.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
		.body(result);
	}
	@DeleteMapping("/buildlog/{id}")
	@Timed
	public ResponseEntity<Void> deleteBuildlog(@PathVariable Long id) {
		log.debug("REST request to delete Bulidlog : {}", id);
		buildlogService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}