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
import com.sensei.app.service.StudentService;
import com.sensei.app.service.dto.StudentDTO;
import com.sensei.app.service.dto.TeachersDTO;
import com.sensei.app.service.impl.StudentServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class StudentResource {
	
	private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	private final String ENTITY_NAME="Student";
	
	private final StudentService studentService;
	
	public StudentResource(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/student")
	@Timed
	public ResponseEntity<StudentDTO> create( @RequestBody StudentDTO studentDTO)
			throws URISyntaxException {
		log.debug("REST request to save student : {}", studentDTO);
		if (studentDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new student cannot already have an ID")).body(null);
		}
        StudentDTO result = studentService.save(studentDTO);
		return ResponseEntity.created(new URI("/api/student/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}
	
	@GetMapping("/student")
	@Timed
	public ResponseEntity<List<StudentDTO>> getAllStudent(@ApiParam Pageable pageable){
		Page<StudentDTO> page=studentService.findall(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/student");
		  return new ResponseEntity<List<StudentDTO>>(page.getContent(),headers,HttpStatus.OK); 
	}
	
	@GetMapping("/student/{id}")
	@Timed
	public ResponseEntity<Optional<StudentDTO>> getStudentById(@PathVariable Long id){
		Optional<StudentDTO> data=studentService.findOne(id);
		return ResponseEntity.status(HttpStatus.OK)
		        .body(data);
	}
	
	@PutMapping("/student")
	@Timed
	public ResponseEntity<StudentDTO> update( @RequestBody StudentDTO studentDTO)
			throws URISyntaxException {
		log.debug("REST request to save student : {}", studentDTO);
		if (studentDTO.getId() == null) {
			return create(studentDTO);
		}
		 Optional<StudentDTO> student=studentService.findOne(studentDTO.getId());
		 studentDTO.setVersion(student.get().getVersion());
        StudentDTO result = studentService.save(studentDTO);
		return ResponseEntity.created(new URI("/api/student/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}
	@DeleteMapping("/student/{id}")
	@Timed
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		log.debug("REST request to delete student : {}", id);
		studentService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
