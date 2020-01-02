package com.sensei.app.service.mapper;

import com.sensei.app.domain.*;
import com.sensei.app.service.dto.BranchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Branch and its DTO BranchDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BranchMapper extends EntityMapper<BranchDTO, Branch> {



    default Branch fromId(Long id) {
        if (id == null) {
            return null;
        }
        Branch branch = new Branch();
        branch.setId(id);
        return branch;
    }
}
