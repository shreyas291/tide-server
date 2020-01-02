package com.sensei.app.service.mapper;

import com.sensei.app.domain.*;
import com.sensei.app.service.dto.BranchsetBranchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BranchsetBranch and its DTO BranchsetBranchDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BranchsetBranchMapper extends EntityMapper<BranchsetBranchDTO, BranchsetBranch> {



    default BranchsetBranch fromId(Long id) {
        if (id == null) {
            return null;
        }
        BranchsetBranch branchsetBranch = new BranchsetBranch();
        branchsetBranch.setId(id);
        return branchsetBranch;
    }
}
