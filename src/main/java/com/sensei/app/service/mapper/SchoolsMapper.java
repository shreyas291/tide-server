package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Schools;
import com.sensei.app.service.dto.SchoolsDTO;

@Mapper(componentModel = "spring", uses = {})
public interface SchoolsMapper extends EntityMapper<SchoolsDTO,Schools> {
	 default Schools fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        Schools schools = new Schools();
	        schools.setId(id);
	        return schools;
	    }

}
