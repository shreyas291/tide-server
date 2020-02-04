package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.Subjects;
import com.sensei.app.repository.SubjectsRepository;
import com.sensei.app.service.SubjectsService;
import com.sensei.app.service.dto.SubjectsDTO;
import com.sensei.app.service.mapper.SubjectsMapper;

@Service
@Transactional
public class SubjectsServiceImpl implements SubjectsService {

	private final SubjectsMapper subjectsMapper;
	
	private final SubjectsRepository subjectsRepository;
	
	
	
	public SubjectsServiceImpl(SubjectsMapper subjectsMapper, SubjectsRepository subjectsRepository) {
		this.subjectsMapper = subjectsMapper;
		this.subjectsRepository = subjectsRepository;
	}

	@Override
	public SubjectsDTO save(SubjectsDTO subjectsDTO) {
		Subjects subjects=subjectsMapper.toEntity(subjectsDTO);
		subjects=subjectsRepository.save(subjects);
		SubjectsDTO result=subjectsMapper.toDto(subjects);
		return result;
	}

	@Override
	public Page<SubjectsDTO> findall(Pageable pagable) {
		Page<Subjects> result=subjectsRepository.findAll(pagable);
		return result.map(subjects ->subjectsMapper.toDto(subjects));
	}

	@Override
	public Optional<SubjectsDTO> findOne(Long id) {
		Optional<Subjects> result=subjectsRepository.findById(id);
		return result.map(Subjects->subjectsMapper.toDto(Subjects));
	}

	@Override
	public void delete(Long id) {
		subjectsRepository.deleteById(id);		
		
	}

}
