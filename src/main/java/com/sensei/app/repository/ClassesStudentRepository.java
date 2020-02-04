package com.sensei.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.ClassesStudent;

public interface ClassesStudentRepository extends JpaRepository<ClassesStudent,Long> {

}
