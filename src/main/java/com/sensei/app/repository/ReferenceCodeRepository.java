package com.sensei.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sensei.app.domain.ReferenceCode;


/**
 * Spring Data JPA repository for the ReferenceCode entity.
 */
@SuppressWarnings("unused")
public interface ReferenceCodeRepository extends JpaRepository<ReferenceCode, Long> {

	@Query("select distinct rc.classifier from ReferenceCode rc")
	List<String> findDistinctClassifiers();

	List<ReferenceCode> findByStatus(Integer status);

	List<ReferenceCode> findByClassifier(String classifier);

	List<ReferenceCode> findByClassifierAndStatus(String classifier, Integer status);

	List<ReferenceCode> findByClassifierAndParentReferenceCodeAndStatus(String classifier, String parentReferenceCode,
			Integer status);

	@Query("select distinct rc2 from ReferenceCode rc1, ReferenceCode rc2 where rc1.classifier = :classifier and "
			+ "rc1.parentClassifier = rc2.classifier and rc1.status = :status")
	List<ReferenceCode> findParentReferenceCodes(@Param("classifier")String classifier, @Param("status")Integer status);

}
