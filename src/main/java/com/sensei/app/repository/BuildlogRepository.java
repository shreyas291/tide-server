package com.sensei.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.Buildlog;

public interface BuildlogRepository extends JpaRepository<Buildlog,Long>{

}
