package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.TeacherGrade;
import com.sensei.app.repository.TeacherGradeRepository;
import com.sensei.app.service.TeacherGradeService;
import com.sensei.app.service.dto.TeacherGradeDTO;
import com.sensei.app.service.mapper.TeacherGradeMapper;

@Service
@Transactional
public class TeacherGradeServiceImpl implements TeacherGradeService{
	
	private final TeacherGradeRepository teachergradeRepository;
	
	private final TeacherGradeMapper teachergradeMapper;
	
	

	public TeacherGradeServiceImpl(TeacherGradeRepository teachergradeRepository,
			TeacherGradeMapper teachergradeMapper) {
		this.teachergradeRepository = teachergradeRepository;
		this.teachergradeMapper = teachergradeMapper;
	}

	@Override
	public TeacherGradeDTO save(TeacherGradeDTO teacherGradeDTO) {
		TeacherGrade teacherGrade=teachergradeMapper.toEntity(teacherGradeDTO);
		teacherGrade=teachergradeRepository.save(teacherGrade);
		TeacherGradeDTO result=teachergradeMapper.toDto(teacherGrade);
		return result;
	}

	@Override
	public Page<TeacherGradeDTO> findall(Pageable pagable) {
		Page<TeacherGrade> result=teachergradeRepository.findAll(pagable);
		return result.map(TeacherGrade ->teachergradeMapper.toDto(TeacherGrade));
	}

	@Override
	public Optional<TeacherGradeDTO> findOne(Long id) {
		Optional<TeacherGrade>result= teachergradeRepository.findById(id);
		return result.map(TeacherGrade ->teachergradeMapper.toDto(TeacherGrade));
	}

	@Override
	public void delete(Long id) {
		teachergradeRepository.deleteById(id);
		
	}

}
