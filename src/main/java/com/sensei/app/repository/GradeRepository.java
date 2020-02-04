package com.sensei.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.Grade;

public interface GradeRepository extends JpaRepository<Grade,Long>{

}
