package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.TeacherGradeDTO;

public interface TeacherGradeService {
	/*
	 to save the TeacherGrade data
	 */
	TeacherGradeDTO save(TeacherGradeDTO teacherGradeDTO);
	
	/*
	 * to get all the TeacherGrade details
	 */
	
	Page<TeacherGradeDTO> findall(Pageable pagable);
	
	/*
	 * to get the single TeacherGrade details
	 */
	
	 Optional<TeacherGradeDTO> findOne(Long id);
	
	 /*
	  * to delete the TeacherGrade details
	  */
	 
	 void delete(Long id);
	
	
}
