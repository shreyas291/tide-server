package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.Buildlog;
import com.sensei.app.repository.BuildlogRepository;
import com.sensei.app.service.BuildlogService;
import com.sensei.app.service.dto.BuildlogDTO;
import com.sensei.app.service.mapper.BuildlogMapper;

@Service
@Transactional
public class BuildlogServiceImpl implements BuildlogService {

	private final BuildlogRepository buildlogRepository;
	
	private final BuildlogMapper buildlogMapper;
	
	
	public BuildlogServiceImpl(BuildlogRepository buildlogRepository, BuildlogMapper buildlogMapper) {
		this.buildlogRepository = buildlogRepository;
		this.buildlogMapper = buildlogMapper;
	}

	@Override
	public BuildlogDTO save(BuildlogDTO buildlogDTO) {
		Buildlog buildlog=buildlogMapper.toEntity(buildlogDTO);
		buildlog=buildlogRepository.save(buildlog);
		BuildlogDTO result=buildlogMapper.toDto(buildlog);
		return result;
	}

	@Override
	public Page<BuildlogDTO> findall(Pageable pageable) {
		Page<Buildlog> result=buildlogRepository.findAll(pageable);
		return result.map(Buildlog ->buildlogMapper.toDto(Buildlog));
		
	}

	@Override
	public Optional<BuildlogDTO> findOne(Long id) {
	 Optional<Buildlog> result=buildlogRepository.findById(id);
		return result.map(Buildlog->buildlogMapper.toDto(Buildlog));
	}

	@Override
	public void delete(Long id) {
		buildlogRepository.deleteById(id);
		
	}

}
