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
import com.sensei.app.service.ClassesStudentService;
import com.sensei.app.service.dto.ClassesStudentDTO;
import com.sensei.app.service.impl.ClassesStudentServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")

public class ClassesStudentResource {
	
	private final Logger log = LoggerFactory.getLogger(ClassesStudentServiceImpl.class);

	private static final String ENTITY_NAME ="ClassesStudent";
	
	private final ClassesStudentService classesStudentService;
	

	public ClassesStudentResource(ClassesStudentService classesStudentService) {
		this.classesStudentService = classesStudentService;
	}



	@PostMapping("/classesStudent")
	@Timed
	public ResponseEntity<ClassesStudentDTO> create( @RequestBody ClassesStudentDTO classesStudentDTO)
			throws URISyntaxException {
		log.debug("REST request to save ClassesStudent : {}", classesStudentDTO);
		if (classesStudentDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new ClassesStudent cannot already have an ID")).body(null);
		}
		Optional<ClassesStudentDTO> classesstudent=classesStudentService.findOne(classesStudentDTO.getId());
		classesStudentDTO.setVersion(classesstudent.get().getVersion());
		ClassesStudentDTO result = classesStudentService.save(classesStudentDTO);
		return ResponseEntity.created(new URI("/api/classesstudent/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	
	@GetMapping("/classesStudent")
	@Timed
	  public ResponseEntity<List<ClassesStudentDTO>> getAllStudent(@ApiParam Pageable pageable) { 
	 Page<ClassesStudentDTO> page = classesStudentService.findall(pageable);
	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/classesstudent");
	  return new ResponseEntity<List<ClassesStudentDTO>>(page.getContent(),headers,HttpStatus.OK); 
	  }
	
	@GetMapping("/classesStudent/{id}")
	@Timed
	  public ResponseEntity<Optional<ClassesStudentDTO>> getStudentById(@PathVariable Long id) throws URISyntaxException { 
		Optional<ClassesStudentDTO> data=classesStudentService.findOne(id);
		return ResponseEntity.created(new URI("/api/classesstudent/" + data.get().getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, data.get().getId().toString())).body(data);
	  }
	
	
	@PutMapping("/classesStudent")
	@Timed
	public ResponseEntity<ClassesStudentDTO> update( @RequestBody ClassesStudentDTO classesStudentDTO)
			throws URISyntaxException {
		log.debug("REST request to save ReferenceCode : {}", classesStudentDTO);
		if (classesStudentDTO.getId() == null) {
			return create(classesStudentDTO);
		}
		ClassesStudentDTO result = classesStudentService.save(classesStudentDTO);
		return ResponseEntity.ok()
		.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
		.body(result);
	}
	@DeleteMapping("/classesStudent/{id}")
	@Timed
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		log.debug("REST request to delete ClassesStudent : {}", id);
		classesStudentService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}

