package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.TopicDTO;

public interface TopicService {
	
	/*
	 to save the Topic data
	 */
	TopicDTO save(TopicDTO topicDTO);
	
	/*
	 * to get all the Topic details
	 */
	
	Page<TopicDTO> findall(Pageable pageable);
	
	/*
	 * to get the single Topic details
	 */
	
	 Optional<TopicDTO> findOne(Long id);
	
	 /*
	  * to delete the Topic details
	  */
	 
	 void delete(Long id);
}
