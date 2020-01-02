package com.sensei.app.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sensei.app.domain.RoleAuthority;
import com.sensei.app.service.dto.RoleAuthorityDTO;

/**
 * Mapper for the entity ReferenceCode and its DTO ReferenceCodeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RoleAuthorityMapper extends EntityMapper<RoleAuthorityDTO, RoleAuthority> {

    
    default RoleAuthority roleAuthorityFromId(Long id) {
        if (id == null) {
            return null;
        }

        RoleAuthority roleAuthority = new RoleAuthority();
        roleAuthority.setId(id);
        return roleAuthority;
    }
}
