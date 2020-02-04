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
import com.sensei.app.service.TeacherGradeService;
import com.sensei.app.service.dto.TeacherGradeDTO;
import com.sensei.app.service.impl.TeacherGradeServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")

public class TeacherGradeResource {
	
	private final Logger log = LoggerFactory.getLogger(TeacherGradeServiceImpl.class);

	private static final String ENTITY_NAME ="TeacherGrade";
	
	private final TeacherGradeService teachergradeService;
	

	public TeacherGradeResource(TeacherGradeService teachergradeService) {
		this.teachergradeService = teachergradeService;
	}



	@PostMapping("/teachergrade")
	@Timed
	public ResponseEntity<TeacherGradeDTO> create( @RequestBody TeacherGradeDTO teachergradeDTO)
			throws URISyntaxException {
		log.debug("REST request to save TeacherGrade : {}", teachergradeDTO);
		if (teachergradeDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new TeacherGrade cannot already have an ID")).body(null);
		}
		TeacherGradeDTO result = teachergradeService.save(teachergradeDTO);
		return ResponseEntity.created(new URI("/api/teachergrade/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	
	@GetMapping("/teachergrade")
	@Timed
	  public ResponseEntity<List<TeacherGradeDTO>> getAllTeacherGrade(@ApiParam Pageable pageable) { 
	 Page<TeacherGradeDTO> page = teachergradeService.findall(pageable);
	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/teachergrade");
	  return new ResponseEntity<List<TeacherGradeDTO>>(page.getContent(),headers,HttpStatus.OK); 
	  }
	
	@GetMapping("/teachergrade/{id}")
	@Timed
	  public ResponseEntity<Optional<TeacherGradeDTO>> getTeacherGradeById(@PathVariable Long id) throws URISyntaxException { 
	  Optional<TeacherGradeDTO> data= teachergradeService.findOne(id);
	  return ResponseEntity.created(new URI("/api/teachergrade/" + data.get().getId()))
		.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, data.get().getId().toString())).body(data);
	  }
	
	@PutMapping("/teachergrade")
	@Timed
	public ResponseEntity<TeacherGradeDTO> update( @RequestBody TeacherGradeDTO teachergradeDTO)
			throws URISyntaxException {
		log.debug("REST request to save TeacherGrade : {}", teachergradeDTO);
		if (teachergradeDTO.getId() == null) {
			return create(teachergradeDTO);
		}
		TeacherGradeDTO result = teachergradeService.save(teachergradeDTO);
		return ResponseEntity.ok()
		.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
		.body(result);
	}
	@DeleteMapping("/teachergrade/{id}")
	@Timed
	public ResponseEntity<Void> deleteTeacherGrade(@PathVariable Long id) {
		log.debug("REST request to delete TeacherGrade : {}", id);
		teachergradeService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}


