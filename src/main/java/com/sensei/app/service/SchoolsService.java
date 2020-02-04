package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.SchoolsDTO;


public interface SchoolsService {

	/*
	 to save the Schools data
	 */
	SchoolsDTO save(SchoolsDTO schoolsDTO);
	
	/*
	 * to get all the Schools details
	 */
	
	Page<SchoolsDTO> findall(Pageable pagable);
	
	/*
	 * to get the single Schools details
	 */
	
	 Optional<SchoolsDTO> findOne(Long id);
	
	 /*
	  * to delete the Schools details
	  */
	 
	 void delete(Long id);
	 
//	 String getGeoLocation();
//	
}
