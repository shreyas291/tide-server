package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Course;
import com.sensei.app.service.dto.CourseDTO;

@Mapper(componentModel = "spring", uses = {})
public interface CourseMapper extends EntityMapper<CourseDTO,Course>{
	default Course fromId(Long id) {
        if (id == null) {
            return null;
        }
        Course course = new Course();
       course.setId(id);
        return course;
    }
}
