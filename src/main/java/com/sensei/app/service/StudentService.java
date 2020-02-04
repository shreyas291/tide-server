package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.StudentDTO;

public interface StudentService {
	/*
	 to save the Student data
	 */
	StudentDTO save(StudentDTO studentDTO);
	
	/*
	 * to get all the Student details
	 */
	
	Page<StudentDTO> findall(Pageable pagable);
	
	/*
	 * to get the single Student details
	 */
	
	 Optional<StudentDTO> findOne(Long id);
	
	 /*
	  * to delete the Student details
	  */
	 
	 void delete(Long id);
	
}
