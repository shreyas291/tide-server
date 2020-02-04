package com.sensei.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.Schools;

public interface SchoolsRepository extends JpaRepository<Schools,Long>{
	
	List findByid(Long id);

}
