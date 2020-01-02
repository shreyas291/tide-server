package com.sensei.app.repository;

import com.sensei.app.domain.Authority;
import com.sensei.app.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface FileRepository extends JpaRepository<File, String> {
    File findOneById(Long id);
}
