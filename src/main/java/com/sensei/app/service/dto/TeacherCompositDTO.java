package com.sensei.app.service.dto;

import java.io.Serializable;
import java.util.List;

public class TeacherCompositDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private TeachersDTO teacherDTO;
	
	private List<TeacherGradeDTO> teacherGradeDTO;

	public TeachersDTO getTeacherDTO() {
		return teacherDTO;
	}

	public void setTeacherDTO(TeachersDTO teacherDTO) {
		this.teacherDTO = teacherDTO;
	}

	public List<TeacherGradeDTO> getTeacherGradeDTO() {
		return teacherGradeDTO;
	}

	public void setTeacherGradeDTO(List<TeacherGradeDTO> teacherGradeDTO) {
		this.teacherGradeDTO = teacherGradeDTO;
	}
}
