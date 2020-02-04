package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.TeacherGrade;
import com.sensei.app.service.dto.TeacherGradeDTO;

@Mapper(componentModel = "spring", uses = {})
public interface TeacherGradeMapper extends EntityMapper<TeacherGradeDTO,TeacherGrade> {
	 default TeacherGrade fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        TeacherGrade teacherGrade = new TeacherGrade();
	        teacherGrade.setId(id);
	        return teacherGrade;
	    }

}

