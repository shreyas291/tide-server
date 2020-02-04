package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.Device;
import com.sensei.app.repository.DeviceRepository;
import com.sensei.app.service.DeviceService;
import com.sensei.app.service.dto.DeviceDTO;
import com.sensei.app.service.mapper.DeviceMapper;

@Service
@Transactional

public class DeviceServiceImpl implements DeviceService
{

	private final DeviceRepository deviceRepository;
	
	private final DeviceMapper deviceMapper;
	
	public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceMapper deviceMapper) {
		this.deviceRepository = deviceRepository;
		this.deviceMapper = deviceMapper;
	}

	@Override
	public DeviceDTO save(DeviceDTO deviceDTO) {
		Device device=deviceMapper.toEntity(deviceDTO);
		device=deviceRepository.save(device);
		DeviceDTO result=deviceMapper.toDto(device);
		return result;
	}

	@Override
	public Page<DeviceDTO> findall(Pageable pagable) {
		Page<Device> result=deviceRepository.findAll(pagable);
		return result.map(Device->deviceMapper.toDto(Device));
	}

	@Override
	public Optional<DeviceDTO> findOne(Long id) {
		Optional<Device> result=deviceRepository.findById(id);
		return result.map(Device->deviceMapper.toDto(Device));
	}

	@Override
	public void delete(Long id) { 
		deviceRepository.deleteById(id);
		
	}

}
