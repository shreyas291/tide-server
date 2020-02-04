package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Subjects;
import com.sensei.app.service.dto.SubjectsDTO;

@Mapper(componentModel = "spring", uses = {})
public interface SubjectsMapper extends EntityMapper<SubjectsDTO,Subjects> {
	 default Subjects fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        Subjects subjects = new Subjects();
	        subjects.setId(id);
	        return subjects;
	    }

}
