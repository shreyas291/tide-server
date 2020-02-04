package com.sensei.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {

}
