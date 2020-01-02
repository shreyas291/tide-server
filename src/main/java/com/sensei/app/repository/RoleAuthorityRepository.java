package com.sensei.app.repository;

import com.sensei.app.domain.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Long> {
	List<RoleAuthority> findByRoleCode(String roleCode);

	@Modifying
	@Query("delete from RoleAuthority ra where ra.roleCode = :roleCode")
	void deleteAuthoritiesFor(@Param("roleCode") String roleCode);
	
	@Query("select ra.authorityName from RoleAuthority ra where ra.roleCode = :roleCode")
	List<String> getAuthoritiesByRoleCode(@Param("roleCode") String roleCode);

}
