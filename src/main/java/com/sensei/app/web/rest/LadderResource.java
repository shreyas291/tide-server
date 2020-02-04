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
import com.sensei.app.domain.Ladder;
import com.sensei.app.service.LadderService;
import com.sensei.app.service.dto.LadderDTO;
import com.sensei.app.service.impl.LadderServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class LadderResource {
	
	private final Logger log = LoggerFactory.getLogger(LadderServiceImpl.class);

	private static final String ENTITY_NAME ="Ladder";
	
	private final LadderService ladderService;


	public LadderResource(LadderService ladderService) {
		super();
		this.ladderService = ladderService;
	}


	@PostMapping("/ladder")
	@Timed
	public ResponseEntity<LadderDTO> create( @RequestBody LadderDTO ladderDTO)
			throws URISyntaxException {
		log.debug("REST request to save Ladder : {}", ladderDTO);
		if (ladderDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new Ladder cannot already have an ID")).body(null);
		}
		LadderDTO result = ladderService.save(ladderDTO);
		return ResponseEntity.created(new URI("/api/ladder/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	
	@GetMapping("/ladder")
	@Timed
	  public ResponseEntity<List<LadderDTO>> getAllladder(@ApiParam Pageable pageable) { 
	 Page<LadderDTO> page =ladderService.findall(pageable);
	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/ladder");
	  return new ResponseEntity<List<LadderDTO>>(page.getContent(),headers,HttpStatus.OK); 
	  }
	
	@PutMapping("/ladder")
	@Timed
	public ResponseEntity<LadderDTO> update( @RequestBody LadderDTO ladderDTO)
			throws URISyntaxException {
		log.debug("REST request to save Ladder : {}", ladderDTO);
		if (ladderDTO.getId() == null) {
			return create(ladderDTO);
		}
		Optional<LadderDTO> ladder=ladderService.findOne(ladderDTO.getId());
		ladderDTO.setVersion(ladder.get().getVersion());
		LadderDTO result = ladderService.save(ladderDTO);
		return ResponseEntity.ok()
		.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
		.body(result);
	}
		
		@GetMapping("/ladder/{id}")
		@Timed
		  public ResponseEntity<Optional<LadderDTO>> getladderById(@PathVariable Long id) throws URISyntaxException { 
		 Optional<LadderDTO> data =ladderService.findOne(id);
		 return ResponseEntity.created(new URI("/api/ladder/" + data.get().getId()))
					.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, data.get().getId().toString())).body(data);
		  }
		

	@DeleteMapping("/ladder/{id}")
	@Timed
	public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
		log.debug("REST request to delete Ladder : {}", id);
		ladderService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
