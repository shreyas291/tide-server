package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.Milestones;
import com.sensei.app.repository.MilestonesRepository;
import com.sensei.app.service.MilestonesService;
import com.sensei.app.service.dto.MilestonesDTO;
import com.sensei.app.service.mapper.MilestonesMapper;

@Service
@Transactional

public class MilestonesServiceImpl implements MilestonesService {
	
	private final MilestonesRepository milestonesRepository;
	
	private final MilestonesMapper milestonesMapper;
	
	

	public MilestonesServiceImpl(MilestonesRepository milestonesRepository, MilestonesMapper milestonesMapper) {
		this.milestonesRepository = milestonesRepository;
		this.milestonesMapper = milestonesMapper;
	}

	@Override
	public MilestonesDTO save(MilestonesDTO milestonesDTO) {
		Milestones milestones=milestonesMapper.toEntity(milestonesDTO);
		milestones=milestonesRepository.save(milestones);
		MilestonesDTO result=milestonesMapper.toDto(milestones);
		return result;
	}

	@Override
	public Page<MilestonesDTO> findall(Pageable pageable) {
		Page<Milestones> result=milestonesRepository.findAll(pageable);
		return result.map(Milestones ->milestonesMapper.toDto(Milestones));
	}

	@Override
	public Optional<MilestonesDTO> findOne(Long id) {
		Optional<Milestones>result=milestonesRepository.findById(id);
		return result.map(Milestones->milestonesMapper.toDto(Milestones));
	}

	@Override
	public void delete(Long id) {
		milestonesRepository.deleteById(id);
	}

}
