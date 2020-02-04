package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.DeviceDTO;

public interface DeviceService {

	/*
	 to save the Device data
	 */
	DeviceDTO save(DeviceDTO deviceDTO);
	
	/*
	 * to get all the Device details
	 */
	
	Page<DeviceDTO> findall(Pageable pagable);
	
	/*
	 * to get the single Device details
	 */
	
	 Optional<DeviceDTO> findOne(Long id);
	
	 /*
	  * to delete the Device details
	  */
	 
	 void delete(Long id);


}
