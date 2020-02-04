package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.Grade;
import com.sensei.app.repository.GradeRepository;
import com.sensei.app.service.GradeService;
import com.sensei.app.service.dto.GradeDTO;
import com.sensei.app.service.mapper.GradeMapper;

@Service
@Transactional
public class GradeServiceImpl implements GradeService{
	
	private final GradeRepository gradeRepository;
	
	private final GradeMapper gradeMapper;

	
	public GradeServiceImpl(GradeRepository gradeRepository, GradeMapper gradeMapper) {
		this.gradeRepository = gradeRepository;
		this.gradeMapper = gradeMapper;
	}

	@Override
	public GradeDTO save(GradeDTO gradeDTO) {
		Grade grade=gradeMapper.toEntity(gradeDTO);
		grade=gradeRepository.save(grade);
		GradeDTO result=gradeMapper.toDto(grade);
		return result;
	}

	@Override
	public Page<GradeDTO> findall(Pageable pagable) {
		Page<Grade> result=gradeRepository.findAll(pagable);
		return result.map(Grade ->gradeMapper.toDto(Grade));
	}

	@Override
	public Optional<GradeDTO> findOne(Long id) {
		Optional<Grade> result=gradeRepository.findById(id);
		return result.map(Grade ->gradeMapper.toDto(Grade));
	}

	@Override
	public void delete(Long id) {
		gradeRepository.deleteById(id);		
	}

}
