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
import com.sensei.app.service.TopicService;
import com.sensei.app.service.dto.TopicDTO;
import com.sensei.app.service.impl.TopicServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")

public class TopicResource {

	private final Logger log = LoggerFactory.getLogger(TopicServiceImpl.class);

	private final String ENTITY_NAME = "Topic";

	private final TopicService topicService;

	public TopicResource(TopicService topicService) {
		super();
		this.topicService = topicService;
	}

	@PostMapping("/topic")
	@Timed
	public ResponseEntity<TopicDTO> create(@RequestBody TopicDTO topicDTO) throws URISyntaxException {
		log.debug("REST request to save Topic : {}", topicDTO);
		if (topicDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new Topic cannot already have an ID"))
					.body(null);
		}
		TopicDTO result = topicService.save(topicDTO);
		return ResponseEntity.created(new URI("/api/topic/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	@GetMapping("/topic")
	@Timed
	public ResponseEntity<List<TopicDTO>> getAllTOpic(@ApiParam Pageable pageable) {
		Page<TopicDTO> page = topicService.findall(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/topic");
		return new ResponseEntity<List<TopicDTO>>(page.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping("/topic/{id}")
	@Timed
	public ResponseEntity<Optional<TopicDTO>> getTopicById(@PathVariable Long id) throws URISyntaxException {
		Optional<TopicDTO> data = topicService.findOne(id);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, data.get().getId().toString())).body(data);
	}

	@PutMapping("/topic")
	@Timed
	public ResponseEntity<TopicDTO> update(@RequestBody TopicDTO topicDTO) throws URISyntaxException {
		log.debug("REST request to save Topic : {}", topicDTO);
		if (topicDTO.getId() == null) {
			return create(topicDTO);
		}
		Optional<TopicDTO> topic=topicService.findOne(topicDTO.getId());
		topicDTO.setVersion(topic.get().getVersion());
		TopicDTO result = topicService.save(topicDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	@DeleteMapping("/topic/{id}")
	@Timed
	public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
		log.debug("REST request to delete Topic : {}", id);
		topicService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
