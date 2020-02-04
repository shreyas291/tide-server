package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.Class;
import com.sensei.app.repository.ClassRepository;
import com.sensei.app.service.ClassService;
import com.sensei.app.service.dto.ClassDTO;
import com.sensei.app.service.mapper.ClassMapper;

@Service
@Transactional

public class ClassServiceImpl implements ClassService{
	
	private final ClassRepository classRepository;
	
	private final ClassMapper classMapper;
	


	public ClassServiceImpl(ClassRepository classRepository, ClassMapper classMapper) {
		super();
		this.classRepository = classRepository;
		this.classMapper = classMapper;
	}

	@Override
	public ClassDTO save(ClassDTO classDTO) {
		Class clas=classMapper.toEntity(classDTO);
		clas=classRepository.save(clas);
		ClassDTO result=classMapper.toDto(clas);
		return result;
	}

	@Override
	public Page<ClassDTO> findall(Pageable pageable) {
		Page<Class> result=classRepository.findAll(pageable);
		return result.map(Class ->classMapper.toDto(Class));
	
	}

	@Override
	public Optional<ClassDTO> findOne(Long id) {
		Optional<Class>result= classRepository.findById(id);
		return result.map(Class ->classMapper.toDto(Class));
	}

	@Override
	public void delete(Long id) {
		classRepository.deleteById(id);
		
	}

}
