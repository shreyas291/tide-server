package com.sensei.app.service.mapper;

import com.sensei.app.domain.*;
import com.sensei.app.service.dto.BranchsetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Branchset and its DTO BranchsetDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BranchsetMapper extends EntityMapper<BranchsetDTO, Branchset> {



    default Branchset fromId(Long id) {
        if (id == null) {
            return null;
        }
        Branchset branchset = new Branchset();
        branchset.setId(id);
        return branchset;
    }
}
