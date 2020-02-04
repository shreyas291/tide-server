package com.sensei.app.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensei.app.domain.TeacherGrade;
import com.sensei.app.domain.Teachers;
import com.sensei.app.repository.TeacherGradeRepository;
import com.sensei.app.repository.TeachersRepository;
import com.sensei.app.service.TeachersService;
import com.sensei.app.service.dto.TeacherCompositDTO;
import com.sensei.app.service.dto.TeacherGradeDTO;
import com.sensei.app.service.dto.TeachersDTO;
import com.sensei.app.service.mapper.TeacherGradeMapper;
import com.sensei.app.service.mapper.TeachersMapper;

@Service
@Transactional
public class TeachersServiceImpl implements TeachersService{
	
	
	private TeachersRepository teachersRepository;
	private TeachersMapper teachersMapper;	
	private TeacherGradeRepository teacherGradeRepository;
	private TeacherGradeMapper teacherGradeMapper;

	public TeachersServiceImpl(TeachersRepository teachersRepository, TeachersMapper teachersMapper, TeacherGradeRepository teacherGradeRepository, TeacherGradeMapper teacherGradeMapper) {		
		this.teachersRepository = teachersRepository;
		this.teachersMapper = teachersMapper;
		this.teacherGradeRepository = teacherGradeRepository;
		this.teacherGradeMapper = teacherGradeMapper;
	}

	@Override
	public TeachersDTO save(TeachersDTO teacherDTO) {
		Teachers teachers=teachersMapper.toEntity(teacherDTO);
		teachers=teachersRepository.save(teachers);
		TeachersDTO result=teachersMapper.toDto(teachers);
		return result;
	}

	@Override
	public Page<TeachersDTO> findall(Pageable pagable) {
		List<Integer> gradeIds = null;
		Page<TeachersDTO> result= teachersRepository.findAll(pagable).map(teachersMapper :: toDto);
		for(TeachersDTO teachersDTO: result) {
			List<TeacherGrade> teacherGrades = teacherGradeRepository.findByTeacherId(teachersDTO.getId().intValue());
			gradeIds = teacherGrades.stream().map(i -> i.getGradeId()).collect(Collectors.toList());
			teachersDTO.setGradeIds(gradeIds);
		}
		
		return result;
	}

	@Override
	public Optional<TeachersDTO> findOne(Long id) {
		Optional<Teachers> result=teachersRepository.findById(id);
		return result.map(Teachers ->teachersMapper.toDto(Teachers));
	}

	@Override
	public void delete(Long id) {
	   teachersRepository.deleteById(id);
		
	}

	@Override
	public TeacherCompositDTO findTeacherObject(Long id) {
		
		Optional<Teachers> result=teachersRepository.findById(id);
		TeachersDTO teacherDTO = result.map(Teachers ->teachersMapper.toDto(Teachers)).get();		
		List<TeacherGradeDTO> teacherGrade = teacherGradeMapper.toDto(teacherGradeRepository.findByTeacherId(id.intValue())); 
		TeacherCompositDTO compositeDTO = new TeacherCompositDTO();
		compositeDTO.setTeacherDTO(teacherDTO);
		compositeDTO.setTeacherGradeDTO(teacherGrade);
		 return compositeDTO;
	}
}
