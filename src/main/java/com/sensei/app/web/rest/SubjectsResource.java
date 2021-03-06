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
import com.sensei.app.service.SubjectsService;
import com.sensei.app.service.TeachersService;
import com.sensei.app.service.dto.SubjectsDTO;
import com.sensei.app.service.dto.TeachersDTO;
import com.sensei.app.service.impl.SubjectsServiceImpl;
import com.sensei.app.service.impl.TeachersServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class SubjectsResource {

	private final Logger log = LoggerFactory.getLogger(SubjectsServiceImpl.class);

	private static final String ENTITY_NAME ="Subjects";
	
	private final SubjectsService subjectsService;
	

	public SubjectsResource(SubjectsService subjectsService) {
		this.subjectsService = subjectsService;
	}

 

	@PostMapping("/subjects")
	@Timed
	public ResponseEntity<SubjectsDTO> create( @RequestBody SubjectsDTO subjectsDTO)
			throws URISyntaxException {
		log.debug("REST request to save Subjects : {}", subjectsDTO);
		if (subjectsDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new subjects cannot already have an ID")).body(null);
		}
		SubjectsDTO result = subjectsService.save(subjectsDTO);
		return ResponseEntity.created(new URI("/api/subjects/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	
	@GetMapping("/subjects")
	@Timed
	  public ResponseEntity<List<SubjectsDTO>> getAllSubjects(@ApiParam Pageable pageable) { 
	 Page<SubjectsDTO> page = subjectsService.findall(pageable);
	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/subjects");
	  return new ResponseEntity<List<SubjectsDTO>>(page.getContent(),headers,HttpStatus.OK); 
	  }
	

	@GetMapping("/subjects/{id}")
	@Timed
	  public ResponseEntity<Optional<SubjectsDTO>> getSubjectsById(@PathVariable Long id) throws URISyntaxException { 
	 Optional<SubjectsDTO> data=subjectsService.findOne(id);
	 return ResponseEntity.created(new URI("/api/subjects/" + data.get().getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, data.get().getId().toString())).body(data);
	  }
	
	
	@PutMapping("/subjects")
	@Timed
	public ResponseEntity<SubjectsDTO> update( @RequestBody SubjectsDTO subjectsDTO)
			throws URISyntaxException {
		log.debug("REST request to save Subjects : {}", subjectsDTO);
		if (subjectsDTO.getId() == null) {
			return create(subjectsDTO);
		}
		Optional<SubjectsDTO> subjects=subjectsService.findOne(subjectsDTO.getId());
		subjectsDTO.setVersion(subjects.get().getVersion());
		SubjectsDTO result = subjectsService.save(subjectsDTO);
		return ResponseEntity.ok()
		.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
		.body(result);
	}
	@DeleteMapping("/subjects/{id}")
	@Timed
	public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
		log.debug("REST request to delete Subjects : {}", id);
		subjectsService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
