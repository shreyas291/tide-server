package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.ClassesStudent;
import com.sensei.app.repository.ClassesStudentRepository;
import com.sensei.app.service.ClassesStudentService;
import com.sensei.app.service.dto.ClassesStudentDTO;
import com.sensei.app.service.mapper.ClassesStudentMapper;

@Service
@Transactional
public class ClassesStudentServiceImpl implements ClassesStudentService {
	
private final ClassesStudentRepository classesStudentRepository;
	
	private final ClassesStudentMapper classesStudentMapper;
	
	

	public ClassesStudentServiceImpl(ClassesStudentRepository classesStudentRepository,
			ClassesStudentMapper classesStudentMapper) {
		this.classesStudentRepository = classesStudentRepository;
		this.classesStudentMapper = classesStudentMapper;
	}

	@Override
	public ClassesStudentDTO save(ClassesStudentDTO classesStudentDTO) {
		ClassesStudent classesStudent=classesStudentMapper.toEntity(classesStudentDTO);
		classesStudent=classesStudentRepository.save(classesStudent);
		ClassesStudentDTO result=classesStudentMapper.toDto(classesStudent);
		return result;
	}

	@Override
	public Page<ClassesStudentDTO> findall(Pageable pageable) {
		Page<ClassesStudent> result=classesStudentRepository.findAll(pageable);
		return result.map(ClassesStudent ->classesStudentMapper.toDto(ClassesStudent));
	}

	@Override
	public Optional<ClassesStudentDTO> findOne(Long id) {
		Optional<ClassesStudent> result=classesStudentRepository.findById(id);
		return result.map(ClassesStudent->classesStudentMapper.toDto(ClassesStudent));
	}

	@Override
	public void delete(Long id) {
		classesStudentRepository.deleteById(id);
		
	}

}
