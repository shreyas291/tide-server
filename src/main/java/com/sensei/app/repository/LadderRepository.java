package com.sensei.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.Ladder;

public interface LadderRepository extends JpaRepository<Ladder,Long> {

}
