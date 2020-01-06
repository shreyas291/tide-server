package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Device;
import com.sensei.app.service.dto.DeviceDTO;

/**
 * Mapper for the entity ReferenceCode and its DTO ReferenceCodeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DeviceCodeMapper extends EntityMapper<DeviceDTO, Device>{
	
	default Device deviceFromId(Integer id) {
		
		if(id == null) {
			return null;
			
		}
		Device device= new Device();
		device.setId(id);
		return device;
	}
	

}
