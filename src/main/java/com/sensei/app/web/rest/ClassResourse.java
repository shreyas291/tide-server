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
import com.sensei.app.service.ClassService;
import com.sensei.app.service.dto.ClassDTO;
import com.sensei.app.service.impl.ClassServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")

public class ClassResourse {
	
	private final Logger log = LoggerFactory.getLogger(ClassServiceImpl.class);
	
	private final String ENTITY_NAME="class";

	private final ClassService classService;

	
	
	public ClassResourse(ClassService classService) {
		this.classService = classService;
	}


	@PostMapping("/class")
	@Timed
	public ResponseEntity<ClassDTO> create( @RequestBody ClassDTO classDTO)
			throws URISyntaxException {
		log.debug("REST request to save class : {}", classDTO);
		if (classDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new class cannot already have an ID")).body(null);
		}
		ClassDTO result = classService.save(classDTO);
		return ResponseEntity.created(new URI("/api/class/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	
	@GetMapping("/class")
	@Timed
	  public ResponseEntity<List<ClassDTO>> getAllclass(@ApiParam Pageable pageable) { 
	 Page<ClassDTO> page = classService.findall(pageable);
	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/class");
	  return new ResponseEntity<List<ClassDTO>>(page.getContent(),headers,HttpStatus.OK); 
	  }
	
	@GetMapping("/class/{id}")
	@Timed
	  public ResponseEntity<Optional<ClassDTO>> getclassById(@PathVariable Long id) throws URISyntaxException { 
	  Optional<ClassDTO> data= classService.findOne(id);
	  return ResponseEntity.created(new URI("/api/class/" + data.get().getId()))
		.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, data.get().getId().toString())).body(data);
	  }
	
	@PutMapping("/class")
	@Timed
	public ResponseEntity<ClassDTO> update( @RequestBody ClassDTO classDTO)
			throws URISyntaxException {
		log.debug("REST request to save class : {}", classDTO);
		if (classDTO.getId() == null) {
			return create(classDTO);
		}
		ClassDTO result = classService.save(classDTO);
		return ResponseEntity.ok()
		.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
		.body(result);
	}
	@DeleteMapping("/class/{id}")
	@Timed
	public ResponseEntity<Void> deleteclass(@PathVariable Long id) {
		log.debug("REST request to delete class : {}", id);
		classService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
