package com.ongc.mappper;

import com.ongc.dto.requestDto.PolicyRqtDto;
import com.ongc.dto.responseDto.RoleDetailsDto;
import com.ongc.dto.responseDto.UserDetailsDto;
import com.ongc.model.PolicyMstModel;
import com.ongc.model.RoleMstModel;
import com.ongc.model.UserMstModel;

@Mapper(componentModel = "spring")
public interface UserRoleDetailsMapper {

	PolicyDtoToPolicyMstMapper mapper = Mappers.getMapper(PolicyDtoToPolicyMstMapper.class);

	@Mapping(source = "id", target = "userId", qualifiedByName="LongToInteger")
	@Mapping(source = "contactNumber", target = "mobileNo")
	UserDetailsDto userToDto(UserMstModel user);

	@Mapping(source = "id", target = "roleId", qualifiedByName="LongToInteger")
	@Mapping(source = "role", target = "roleName")
	RoleDetailsDto roleToDto(RoleMstModel role);
	
	@Named("LongToInteger")
	default Integer longToInteger(Long id) {
		return id!=null ? id.intValue() : null;
	}

}
