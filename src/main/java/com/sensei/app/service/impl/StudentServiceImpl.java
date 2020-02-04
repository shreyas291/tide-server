package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.Student;
import com.sensei.app.repository.StudentRepository;
import com.sensei.app.service.StudentService;
import com.sensei.app.service.dto.StudentDTO;
import com.sensei.app.service.mapper.StudentMapper;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	 private final StudentRepository studentRepository;
	 private final StudentMapper studentMapper;

	public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
		this.studentRepository = studentRepository;
		this.studentMapper = studentMapper;
	}

	@Override
	public StudentDTO save(StudentDTO studentDTO) {
		Student student=studentMapper.toEntity(studentDTO);
		student=studentRepository.save(student);
		StudentDTO result=studentMapper.toDto(student);
		return result;
	}

	@Override
	public Page<StudentDTO> findall(Pageable pageable) {
		Page<Student> result=studentRepository.findAll(pageable); 
		return result.map(Student ->studentMapper.toDto(Student));
	}

	@Override
	public Optional<StudentDTO> findOne(Long id) {
		Optional<Student> result=studentRepository.findById(id);
		return result.map(Student->studentMapper.toDto(Student));
	}

	@Override
	public void delete(Long id) {
	   studentRepository.deleteById(id);
		
	}

}
