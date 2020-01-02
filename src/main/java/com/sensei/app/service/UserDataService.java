package com.sensei.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sensei.app.service.dto.UserDataDTO;

public interface UserDataService {

	Page<UserDataDTO> getAllUsers(Pageable pageable);

	UserDataDTO savingUserData(UserDataDTO userDataDTO);
	
	UserDataDTO updatingUserData(UserDataDTO userDataDTO);

	UserDataDTO getData(Long id);

	void delete(Long id);

}
