package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Buildlog;
import com.sensei.app.service.dto.BuildlogDTO;

@Mapper(componentModel = "spring", uses = {})
public interface BuildlogMapper extends EntityMapper<BuildlogDTO,Buildlog>{
	default Buildlog fromId(Long id) {
        if (id == null) {
            return null;
        }
        Buildlog buildlog = new Buildlog();
        buildlog.setId(id);
        return buildlog;
    }
}
