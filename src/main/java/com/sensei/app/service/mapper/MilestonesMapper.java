package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Milestones;
import com.sensei.app.service.dto.MilestonesDTO;

@Mapper(componentModel = "spring", uses = {})
public interface MilestonesMapper extends EntityMapper<MilestonesDTO,Milestones>{
	default Milestones fromId(Long id) {
        if (id == null) {
            return null;
        }
        Milestones milestones = new Milestones();
        milestones.setId(id);
        return milestones;
    }
}
