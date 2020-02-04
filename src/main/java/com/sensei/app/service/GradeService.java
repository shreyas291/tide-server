package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.GradeDTO;

public interface GradeService {

	/*
	 to save the Grade data
	 */
	GradeDTO save(GradeDTO gradeDTO);
	
	/*
	 * to get all the Grade details
	 */
	
	Page<GradeDTO> findall(Pageable pagable);
	
	/*
	 * to get the single Grade details
	 */
	
	 Optional<GradeDTO> findOne(Long id);
	
	 /*
	  * to delete the Grade details
	  */
	 
	 void delete(Long id);

}
