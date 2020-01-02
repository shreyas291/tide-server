package com.sensei.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensei.app.domain.UserData;
import com.sensei.app.repository.UserDataRepository;
import com.sensei.app.service.UserDataService;
import com.sensei.app.service.dto.UserDataDTO;
import com.sensei.app.service.mapper.UserDataMapper;

@Service
@Transactional
public class UserDataServiceImpl implements UserDataService {
	
	@Autowired
	private UserDataRepository userDataRepository;
	
	@Autowired
	private UserDataMapper userDataMapper;
	
	@Override
	public PageImpl<UserDataDTO> getAllUsers(Pageable pageable) {
		Page<UserData> page = userDataRepository.findAll(pageable);
		List<UserDataDTO> userDataDTOList = userDataMapper.userDataToUserDataDTOs(page.getContent());
		return new PageImpl<UserDataDTO>(userDataDTOList, pageable, page.getTotalElements());
	}
	
	@Override
	public UserDataDTO savingUserData(UserDataDTO userData) {
		UserData user = userDataRepository.saveAndFlush(userDataMapper.userDataDTOToUserData(userData));
		return userDataMapper.userDataToUserDataDTO(user);
	}
	
	@Override
	public UserDataDTO updatingUserData(UserDataDTO userData) {
		UserData user = userDataRepository.findOneById(userData.getId());
		user.setEmail(userData.getEmail());
		user.setFirstName(userData.getFirstName());
		user.setImageUrl(userData.getImageUrl());
		user.setLangKey(userData.getLangKey());
		user.setLastName(userData.getLastName());
		user.setLogin(userData.getLogin());
		user.setRole(userData.getRole());
		
		UserData afterUserDataUpdate = userDataRepository.saveAndFlush(user);
		return userDataMapper.userDataToUserDataDTO(afterUserDataUpdate);
		
	}
	
	@Override
	public UserDataDTO getData(Long id) {
		UserData user = userDataRepository.findOneById(id);
		return userDataMapper.userDataToUserDataDTO(user);
	}

	@Override
    public void delete(Long id) {
        userDataRepository.deleteById(id);
    }
}
