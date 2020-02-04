package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.Schools;
import com.sensei.app.repository.SchoolsRepository;
import com.sensei.app.service.SchoolsService;
import com.sensei.app.service.dto.SchoolsDTO;
import com.sensei.app.service.mapper.SchoolsMapper;

@Service
@Transactional
public class SchoolsServiceImpl implements SchoolsService{
	
	private final SchoolsRepository schoolsRepository;
	
	private final SchoolsMapper schoolsMapper;
	


	public SchoolsServiceImpl(SchoolsRepository schoolsRepository, SchoolsMapper schoolsMapper) {
		this.schoolsRepository = schoolsRepository;
		this.schoolsMapper = schoolsMapper;

	}

	@Override
	public SchoolsDTO save(SchoolsDTO schoolsDTO) {
		Schools schools=schoolsMapper.toEntity(schoolsDTO);
		schools=schoolsRepository.save(schools);
		SchoolsDTO result=schoolsMapper.toDto(schools);
		return result;
	}

	@Override
	public Page<SchoolsDTO> findall(Pageable pagable) {
		Page<Schools> result=schoolsRepository.findAll(pagable);
		return result.map(Schools ->schoolsMapper.toDto(Schools));
	}

	@Override
	public Optional<SchoolsDTO> findOne(Long id) {
		Optional<Schools> result=schoolsRepository.findById(id);
		return result.map(Schools->schoolsMapper.toDto(Schools));
	}

	@Override
	public void delete(Long id) {
		schoolsRepository.deleteById(id);		
	}

//	@Override
//	public String getGeoLocation() {
//		String geoResponse = applicationProperties.getGeoLocation();
//		return geoResponse;
//	}

}
