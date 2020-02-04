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
import com.sensei.app.service.GradeService;
import com.sensei.app.service.dto.BuildlogDTO;
import com.sensei.app.service.dto.GradeDTO;
import com.sensei.app.service.impl.GradeServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")

public class GradeResource {
	
	private final Logger log = LoggerFactory.getLogger(GradeServiceImpl.class);

	private static final String ENTITY_NAME ="Grade";
	
	private final GradeService gradeService;
	

	public GradeResource(GradeService gradeService) {
		this.gradeService = gradeService;
	}



	@PostMapping("/grade")
	@Timed
	public ResponseEntity<GradeDTO> create( @RequestBody GradeDTO gradeDTO)
			throws URISyntaxException {
		log.debug("REST request to save Grade : {}", gradeDTO);
		if (gradeDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new Grade cannot already have an ID")).body(null);
		}
		GradeDTO result = gradeService.save(gradeDTO);
		return ResponseEntity.created(new URI("/api/grade/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	
	@GetMapping("/grade")
	@Timed
	  public ResponseEntity<List<GradeDTO>> getAllGrade(@ApiParam Pageable pageable) { 
	 Page<GradeDTO> page = gradeService.findall(pageable);
	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/grade");
	  return new ResponseEntity<List<GradeDTO>>(page.getContent(),headers,HttpStatus.OK); 
	  }
	
	@GetMapping("/grade/{id}")
	@Timed
	  public ResponseEntity<Optional<GradeDTO>> getGradeById(@PathVariable Long id ) throws URISyntaxException { 
	 Optional<GradeDTO> data = gradeService.findOne(id);
	 return ResponseEntity.created(new URI("/api/grade/" + data.get().getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, data.get().getId().toString())).body(data);
	  }
	
	@PutMapping("/grade")
	@Timed
	public ResponseEntity<GradeDTO> update( @RequestBody GradeDTO gradeDTO)
			throws URISyntaxException {
		log.debug("REST request to save grade : {}", gradeDTO);
		if (gradeDTO.getId() == null) {
			return create(gradeDTO);
		}
		Optional<GradeDTO> grade=gradeService.findOne(gradeDTO.getId());
		gradeDTO.setVersion(grade.get().getVersion());
		GradeDTO result = gradeService.save(gradeDTO);
		return ResponseEntity.ok()
		.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
		.body(result);
	}
	@DeleteMapping("/grade/{id}")
	@Timed
	public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
		log.debug("REST request to delete grade : {}", id);
		gradeService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
