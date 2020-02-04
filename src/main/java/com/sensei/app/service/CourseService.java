package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.CourseDTO;
import com.sensei.app.service.dto.StudentDTO;

public interface CourseService {
	/*
	 to save the Course data
	 */
	CourseDTO save(CourseDTO courseDTO);
	
	/*
	 * to get all the Course details
	 */
	
	Page<CourseDTO> findall(Pageable pagable);
	
	/*
	 * to get the single Course details
	 */
	
	 Optional<CourseDTO> findOne(Long id);
	
	 /*
	  * to delete the Course details
	  */
	 
	 void delete(Long id);
	

}
