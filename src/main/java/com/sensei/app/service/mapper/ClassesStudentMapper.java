package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.ClassesStudent;
import com.sensei.app.service.dto.ClassesStudentDTO;

@Mapper(componentModel = "spring", uses = {})
public interface ClassesStudentMapper extends EntityMapper<ClassesStudentDTO,ClassesStudent> {
	 default ClassesStudent fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        ClassesStudent classesStudent = new ClassesStudent();
	        classesStudent.setId(id);
	        return classesStudent;
	    }

}
