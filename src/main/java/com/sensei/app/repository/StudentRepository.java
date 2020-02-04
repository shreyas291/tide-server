package com.sensei.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}
