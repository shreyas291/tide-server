package com.sensei.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.Class;

public interface ClassRepository extends JpaRepository<Class,Long>{

}
