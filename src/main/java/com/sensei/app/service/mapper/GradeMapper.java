package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Grade;
import com.sensei.app.service.dto.GradeDTO;

@Mapper(componentModel = "spring", uses = {})
public interface GradeMapper extends EntityMapper<GradeDTO,Grade>{
	 default Grade fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        Grade grade = new Grade();
	        grade.setId(id);
	        return grade;
	    }
}
