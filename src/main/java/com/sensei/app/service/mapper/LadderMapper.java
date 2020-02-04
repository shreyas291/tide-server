package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Ladder;
import com.sensei.app.service.dto.LadderDTO;

@Mapper(componentModel = "spring", uses = {})
public interface LadderMapper extends EntityMapper<LadderDTO,Ladder>{
	 default Ladder fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        Ladder ladder = new Ladder();
	        ladder.setId(id);
	        return ladder;
	    }
}
