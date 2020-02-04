package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.Course;
import com.sensei.app.repository.CourseRepository;
import com.sensei.app.service.CourseService;
import com.sensei.app.service.dto.CourseDTO;
import com.sensei.app.service.mapper.CourseMapper;


@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	
	private final CourseMapper courseMapper;
	
	private final CourseRepository courseRepository;

	public CourseServiceImpl(CourseMapper courseMapper, CourseRepository courseRepository) {
		this.courseMapper = courseMapper;
		this.courseRepository = courseRepository;
	}

	@Override
	public CourseDTO save(CourseDTO courseDTO) {
		Course courses=courseMapper.toEntity(courseDTO);
		courses=courseRepository.save(courses);
		CourseDTO result=courseMapper.toDto(courses);
		return result;
	}

	@Override
	public Page<CourseDTO> findall(Pageable pagable) {
		Page<Course> result=courseRepository.findAll(pagable);
		return result.map(Teachers ->courseMapper.toDto(Teachers));
		
	}

	@Override
	public Optional<CourseDTO> findOne(Long id) {
		Optional<Course> result=courseRepository.findById(id);
		return result.map(Course->courseMapper.toDto(Course));
	}

	@Override
	public void delete(Long id) {
		 courseRepository.deleteById(id);
		
	}

}
