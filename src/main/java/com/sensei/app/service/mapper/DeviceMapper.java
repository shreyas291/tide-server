package com.sensei.app.service.mapper;

import org.springframework.stereotype.Service;

import com.sensei.app.domain.Device;
import com.sensei.app.service.dto.DeviceDTO;

@Service
public class DeviceMapper {
	 public Device deviceDTOToDevice(DeviceDTO deviceDTO) {
	        if(deviceDTO == null)
	            return null;

	        Device device = new Device();
	        device.setId(deviceDTO.getId());
	        device.setDeviceName(deviceDTO.getDeviceName());

	        return device;
	    }
	 public DeviceDTO deviceToDeviceDTO(Device device){
	        if(device == null)
	            return null;

	        DeviceDTO deviceDTO = new DeviceDTO();
	        deviceDTO.setId(device.getId());
	        deviceDTO.setDeviceName(device.getDeviceName());
	        return deviceDTO;
	    }
}
