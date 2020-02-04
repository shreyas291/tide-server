package com.sensei.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.Teachers;

public interface TeachersRepository extends JpaRepository<Teachers, Long>{

	void deleteById(Long id);


	

}
