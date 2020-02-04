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
import com.sensei.app.service.CourseService;
import com.sensei.app.service.dto.CourseDTO;
import com.sensei.app.service.impl.CourseServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")

public class CourseResourse {
	
	
	private final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	private CourseService courseService;
	
	private final String ENTITY_NAME="Courses";
	
	
	
	public CourseResourse(CourseService courseService) {
		this.courseService = courseService;
	}



	@PostMapping("/course")
	@Timed
	public ResponseEntity<CourseDTO> create( @RequestBody CourseDTO courseDTO)
			throws URISyntaxException {
		log.debug("REST request to save Courses : {}", courseDTO);
		if (courseDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new Courses cannot already have an ID")).body(null);
		}
		CourseDTO result = courseService.save(courseDTO);
		return ResponseEntity.created(new URI("/api/course/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}
	
	@GetMapping("/course")
	@Timed
	  public ResponseEntity<List<CourseDTO>> getAllCourses(@ApiParam Pageable pageable) { 
	 Page<CourseDTO> page = courseService.findall(pageable);
	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/course");
	  return new ResponseEntity<List<CourseDTO>>(page.getContent(),headers,HttpStatus.OK); 
	  }
	
	@GetMapping("/course/{id}")
	@Timed
	  public ResponseEntity<Optional<CourseDTO>> getcourseById(@PathVariable Long id) throws URISyntaxException { 
	 java.util.Optional<CourseDTO> data = courseService.findOne(id);
	 return ResponseEntity.created(new URI("/api/course/" + data.get().getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, data.get().getId().toString())).body(data);
	  }
	
	@PutMapping("/course")
	@Timed
	public ResponseEntity<CourseDTO> update( @RequestBody CourseDTO courseDTO)
			throws URISyntaxException {
		log.debug("REST request to save Course : {}", courseDTO);
		if (courseDTO.getId() == null) {
			return create(courseDTO);
		}
		Optional<CourseDTO> course=courseService.findOne(courseDTO.getId());
		courseDTO.setVersion(course.get().getVersion());
		CourseDTO result = courseService.save(courseDTO);
		return ResponseEntity.ok()
		.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
		.body(result);
	}
	@DeleteMapping("/course/{id}")
	@Timed
	public ResponseEntity<Void> deletecourse(@PathVariable Long id) {
		log.debug("REST request to delete Courses : {}", id);
		courseService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
