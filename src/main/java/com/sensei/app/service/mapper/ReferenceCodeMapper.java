package com.sensei.app.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sensei.app.domain.ReferenceCode;
import com.sensei.app.service.dto.ReferenceCodeDTO;

/**
 * Mapper for the entity ReferenceCode and its DTO ReferenceCodeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReferenceCodeMapper extends EntityMapper<ReferenceCodeDTO, ReferenceCode> {

   default ReferenceCode referenceCodeFromId(Long id) {
        if (id == null) {
            return null;
        }
        ReferenceCode referenceCode = new ReferenceCode();
        referenceCode.setId(id);
        return referenceCode;
    }
    

}
