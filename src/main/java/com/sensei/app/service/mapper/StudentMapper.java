package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Student;
import com.sensei.app.service.dto.StudentDTO;

@Mapper(componentModel = "spring", uses = {})
public interface StudentMapper extends EntityMapper<StudentDTO,Student> {
	 default Student fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        Student student=new Student();
	        student.setId(id);
	        return student;
	 }

}
