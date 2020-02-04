package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.BuildlogDTO;

public interface BuildlogService {

	/*
	 to save the Buildlog data
	 */
BuildlogDTO save(BuildlogDTO buildlogDTO);
	
	/*
	 * to get all the Buildlog details
	 */
	
	Page<BuildlogDTO> findall(Pageable pageable);
	
	/*
	 * to get the single Buildlog details
	 */
	
	 Optional<BuildlogDTO> findOne(Long id);
	
	 /*
	  * to delete the Buildlog details
	  */
	 
	 void delete(Long id);

}
