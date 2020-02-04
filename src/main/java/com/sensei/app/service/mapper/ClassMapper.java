package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;
import com.sensei.app.domain.Class;
import com.sensei.app.service.dto.ClassDTO;

@Mapper(componentModel = "spring", uses = {})
public interface ClassMapper extends EntityMapper<ClassDTO,Class>{
	default Class fromId(Long id) {
        if (id == null) {
            return null;
        }
        Class clas = new Class();
        clas.setId(id);
        return clas;
    }
}
