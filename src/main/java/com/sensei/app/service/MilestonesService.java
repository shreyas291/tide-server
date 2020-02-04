package com.sensei.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
  
import com.sensei.app.service.dto.MilestonesDTO;

public interface MilestonesService {
	
	/*
	 to save the Teacher data
	 */
	MilestonesDTO save(MilestonesDTO milestonesDTO);
	
	/*
	 * to get all the Milestones details
	 */
	
	Page<MilestonesDTO> findall(Pageable pageable);
	
	/*
	 * to get the single Milestones details
	 */
	
	 Optional<MilestonesDTO> findOne(Long id);
	
	 /*
	  * to delete the Milestones details
	  */
	 
	 void delete(Long id);

}
