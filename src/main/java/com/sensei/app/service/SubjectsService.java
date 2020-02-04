package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.SubjectsDTO;

public interface SubjectsService {

	/*
	 to save the Subjects data
	 */
	SubjectsDTO save(SubjectsDTO subjectsDTO);
	
	/*
	 * to get all the Subjects details
	 */
	
	Page<SubjectsDTO> findall(Pageable pagable);
	
	/*
	 * to get the single Subjects details
	 */
	
	 Optional<SubjectsDTO> findOne(Long id);
	
	 /*
	  * to delete the Subjects details
	  */
	 
	 void delete(Long id);
	
	}
