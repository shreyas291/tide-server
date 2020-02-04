package com.sensei.app.service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.sensei.app.service.dto.TeacherCompositDTO;
import com.sensei.app.service.dto.TeachersDTO;

public interface TeachersService {
	 
	/*
	 to save the Teacher data
	 */
	TeachersDTO save(TeachersDTO teacherdto);
	
	/*
	 * to get all the teachers details
	 */
	
	Page<TeachersDTO> findall(Pageable pagable);
	
	/*
	 * to get the single teachers details
	 */
	
	 Optional<TeachersDTO> findOne(Long id);
	
	 /*
	  * to delete the teachers details
	  */
	 
	 void delete(Long id);

	TeacherCompositDTO findTeacherObject(Long id);
	
	 /*
	  * to update the teacher details
	  */
	 
}
