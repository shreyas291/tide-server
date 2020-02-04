package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.LadderDTO;


public interface LadderService {

	/*
	 to save the Ladder data
	 */
	LadderDTO save(LadderDTO ladderdto);
	
	/*
	 * to get all the Ladder details
	 */
	
	Page<LadderDTO> findall(Pageable pagable);
	
	/*
	 * to get the single Ladder details
	 */
	
	 Optional<LadderDTO> findOne(Long id);
	
	 /*
	  * to delete the Ladder details
	  */
	 
	 void delete(Long id);
	
	
}
