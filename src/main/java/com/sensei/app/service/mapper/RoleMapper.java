package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Role;
import com.sensei.app.service.dto.RoleDTO;

/**
 * Mapper for the entity ReferenceCode and its DTO ReferenceCodeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {


    default Role roleFromId(Long id) {
        if (id == null) {
            return null;
        }

        Role role = new Role();
        role.setId(id);
        return role;
    }
}
