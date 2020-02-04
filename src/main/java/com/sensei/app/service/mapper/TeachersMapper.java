package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Teachers;
import com.sensei.app.service.dto.TeachersDTO;
@Mapper(componentModel = "spring", uses = {})
public interface TeachersMapper extends EntityMapper<TeachersDTO,Teachers> {
	 default Teachers fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	       Teachers teacher = new Teachers();
	       teacher.setId(id);
	        return teacher;
	    }

}
