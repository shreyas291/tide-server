package com.sensei.app.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sensei.app.domain.UserData;
import com.sensei.app.service.dto.UserDataDTO;

@Mapper(componentModel = "spring", uses = {})
public interface UserDataMapper {

	public UserDataDTO userDataToUserDataDTO(UserData user);
	
	public UserData userDataDTOToUserData(UserDataDTO user);

	public List<UserDataDTO> userDataToUserDataDTOs(List<UserData> content);
    
}
