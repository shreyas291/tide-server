package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Device;
import com.sensei.app.service.dto.DeviceDTO;

@Mapper(componentModel = "spring", uses = {})
public interface DeviceMapper extends EntityMapper<DeviceDTO,Device>{
	default Device fromId(Long id) {
        if (id == null) {
            return null;
        }
        Device device = new Device();
       device.setId(id);
        return device;
    }
}