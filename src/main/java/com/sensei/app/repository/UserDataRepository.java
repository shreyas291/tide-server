package com.sensei.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long>{

	UserData findOneById(Long id);
}
