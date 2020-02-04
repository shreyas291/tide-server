package com.sensei.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.TeacherGrade;

public interface TeacherGradeRepository extends JpaRepository<TeacherGrade,Long> {

	List<TeacherGrade> findByTeacherId(Integer id);

}
