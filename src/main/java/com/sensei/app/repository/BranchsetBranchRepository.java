package com.sensei.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sensei.app.domain.BranchsetBranch;


/**
 * Spring Data  repository for the BranchsetBranch entity.
 */
@Repository
public interface BranchsetBranchRepository extends JpaRepository<BranchsetBranch, Long> {

	BranchsetBranch findByBranchIdAndBranchsetId(Long branchId, Long branchsetId);

	List<BranchsetBranch> findAllByBranchsetId(Long branchsetId);
	
	@Modifying
	@Query("delete from BranchsetBranch br where br.branchsetId = :branchSetId")
	void deleteBranchSetFor(@Param("branchSetId") Long branchSetId);

}
