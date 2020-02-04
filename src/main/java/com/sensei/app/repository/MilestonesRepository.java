package com.sensei.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.Milestones;

public interface MilestonesRepository extends JpaRepository<Milestones,Long> {

}
