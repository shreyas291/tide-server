package com.sensei.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sensei.app.domain.Branch;
import com.sensei.app.domain.Branchset;
import com.sensei.app.service.dto.BranchsetDTO;


/**
 * Spring Data  repository for the Branchset entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchsetRepository extends JpaRepository<Branchset, Long> {

	Branchset findByNameAndCode(String name, String code);

}
