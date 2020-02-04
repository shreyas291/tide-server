package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.ClassesStudentDTO;
import com.sensei.app.service.dto.CourseDTO;

public interface ClassesStudentService {

	/*
	 to save the Teacher data
	 */
	ClassesStudentDTO save(ClassesStudentDTO classesStudentDTO);
	
	/*
	 * to get all the ClassesStudent details
	 */
	
	Page<ClassesStudentDTO> findall(Pageable pageable);
	
	/*
	 * to get the single ClassesStudent details
	 */
	
	 Optional<ClassesStudentDTO> findOne(Long id);
	
	 /*
	  * to delete the ClassesStudent details
	  */
	 
	 void delete(Long id);

}
