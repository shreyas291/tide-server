package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.ClassDTO;

public interface ClassService {

	 
		/*
		 to save the Classes data
		 */
	ClassDTO save(ClassDTO classdto);
		
		/*
		 * to get all the Classes details
		 */
		
		Page<ClassDTO> findall(Pageable pageable);
		
		/*
		 * to get the single Classes details
		 */
		
		 Optional<ClassDTO> findOne(Long id);
		
		 /*
		  * to delete the Classes details
		  */
		 
		 void delete(Long id);

		 
}
