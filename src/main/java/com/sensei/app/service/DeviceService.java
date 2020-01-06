package com.sensei.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensei.app.domain.Device;
import com.sensei.app.repository.DeviceRepository;
import com.sensei.app.service.dto.DeviceDTO;
import com.sensei.app.service.mapper.DeviceCodeMapper;

/**
 * Service class for managing devices.
 */
@Service
@Transactional
public class DeviceService {
	private final Logger log = LoggerFactory.getLogger(DeviceService.class);

	private final DeviceRepository deviceRepository;
	private final DeviceCodeMapper deviceCodeMapper;

	public DeviceService(DeviceRepository deviceRepository, DeviceCodeMapper deviceCodeMapper) {

		this.deviceRepository = deviceRepository;
		this.deviceCodeMapper = deviceCodeMapper;
	}

	/**
	 * Get all the Devices.
	 * 
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public Page<DeviceDTO> getAllManagedDevices(Pageable pageable) {

		Page<Device> result = deviceRepository.findAll(pageable);
		return result.map(device -> deviceCodeMapper.toDto(device));

	}

	/**
	 * Save a Device.
	 *
	 * @param referenceCodeDTO the entity to save
	 * @return the persisted entity
	 */

	public DeviceDTO addDevice(DeviceDTO deviceDTO) {

		Device device = deviceCodeMapper.toEntity(deviceDTO);
		device = deviceRepository.save(device);
		DeviceDTO result = deviceCodeMapper.toDto(device);
		return result;
	}

	/**
	 * Delete the referenceCode by id.
	 *
	 * @param id the id of the entity
	 */
	public void delete(Integer id) {
		log.debug("Request to delete ReferenceCode : {}", id);
		deviceRepository.deleteById(id);
	}

}
