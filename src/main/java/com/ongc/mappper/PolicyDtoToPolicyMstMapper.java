package com.ongc.mappper;

import com.ongc.dto.requestDto.PolicyRqtDto;
import com.ongc.model.PolicyMstModel;

@Mapper(componentModel = "spring")
public class PolicyDtoToPolicyMstMapper {
	
	PolicyDtoToPolicyMstMapper mapper = Mappers.getMapper(PolicyDtoToPolicyMstMapper.class);
	
	@Mapping(source="policyName", target="name")
	@Mapping(source="domainId", target="domainMst.domainId")
	@Mapping(source="creationDate", target="createdOn")
	PolicyMstModel policyDtoToModel(PolicyRqtDto policyRqtDto);
	
}
