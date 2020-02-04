package com.sensei.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.sensei.app.service.TeachersService;
import com.sensei.app.service.dto.TeacherCompositDTO;
import com.sensei.app.service.dto.TeachersDTO;
import com.sensei.app.service.impl.TeachersServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")

public class TeachersResource {
	
	private final Logger log = LoggerFactory.getLogger(TeachersServiceImpl.class);

	private static final String ENTITY_NAME ="Teachers";
	
	private final TeachersService teachersService;
	

	public TeachersResource(TeachersService teachersService) {
		this.teachersService = teachersService;
	}

 

	@PostMapping("/teachers")
	@Timed
	public ResponseEntity<TeachersDTO> create( @RequestBody TeachersDTO teachersDTO)
			throws URISyntaxException {
		log.debug("REST request to save Teachers : {}", teachersDTO);
		if (teachersDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new Teachers cannot already have an ID")).body(null);
		}
		TeachersDTO result = teachersService.save(teachersDTO);
		return ResponseEntity.created(new URI("/api/teachers/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	
	@GetMapping("/teachers")
	@Timed
	  public ResponseEntity<List<TeachersDTO>> getAllteachers(@ApiParam Pageable pageable) { 
	 Page<TeachersDTO> page = teachersService.findall(pageable);
	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/teachers");
	  return new ResponseEntity<List<TeachersDTO>>(page.getContent(),headers,HttpStatus.OK); 
	  }
	
	@GetMapping("/teachers/{id}")
	@Timed
	  public ResponseEntity<Optional<TeachersDTO>> getTeachersById(@PathVariable Long id) { 
		Optional<TeachersDTO> data=teachersService.findOne(id);
	  return  ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, data.get().getId().toString()))
				.body(data); 
	  }
	
	
	@PutMapping("/teachers")
	@Timed
	public ResponseEntity<TeachersDTO> update( @RequestBody TeachersDTO teachersDTO)
			throws URISyntaxException {
		log.debug("REST request to save Teachers : {}", teachersDTO);
		if (teachersDTO.getId() == null) {
			return create(teachersDTO);
		}
		Optional<TeachersDTO> teacher=teachersService.findOne(teachersDTO.getId());
		teachersDTO.setVersion(teacher.get().getVersion());
		TeachersDTO result = teachersService.save(teachersDTO);
		return ResponseEntity.ok()
		.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
		.body(result);
	}
	@DeleteMapping("/teachers/{id}")
	@Timed
	public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
		log.debug("REST request to delete Teachers : {}", id);
		teachersService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
	
	@GetMapping("/teachersRecord/{id}")
	@Timed
	  public ResponseEntity<TeacherCompositDTO> getTeachers(@PathVariable Long id) { 
		TeacherCompositDTO data=teachersService.findTeacherObject(id);
	  return  ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, data.getTeacherDTO().getId().toString()))
				.body(data); 
	  }
}


