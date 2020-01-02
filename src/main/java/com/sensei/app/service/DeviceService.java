package com.sensei.app.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensei.app.config.Constants;
import com.sensei.app.service.dto.DeviceDTO;

/**
 * Service class for managing devices.
 */
@Service
@Transactional
public class DeviceService {
    private final Logger log = LoggerFactory.getLogger(DeviceService.class);
	

    @Transactional(readOnly = true)
    public Page<DeviceDTO> getAllManagedDevices(Pageable pageable) {
    	DeviceDTO device = new DeviceDTO();
    	device.setId((long) 100);
    	
    	ArrayList<DeviceDTO> devices = new ArrayList<DeviceDTO>();
    	devices.add(device);
    	
    	Page<DeviceDTO> page = new PageImpl<>(devices, pageable, devices.size());
        return page;
    }

}
