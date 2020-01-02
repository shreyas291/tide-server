package com.sensei.app.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sensei.app.domain.Branch;


/**
 * Spring Data  repository for the Branch entity.
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

	@Query("select b from Branch b, BranchsetBranch bsb where b.id=bsb.branchId and bsb.branchsetId= :id")
	List<Branch> findBranchesForBranchsetId(@Param("id") Long id);

	@Cacheable("branches")
	Branch findByCode(String branchCode);

}
