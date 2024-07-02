package com.ongc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ongc.Repo.PolicyMstRepo;
import com.ongc.dto.responseDto.PolicyDto;
import com.ongc.model.PolicyMstModel;

@Service
public class ParentPolicyUploadService {
	
	@Autowired
	private PolicyMstRepo policyRepo;
	
	@Autowired
	private ManagePolicyService managePolicyService; 
	
	public List<PolicyDto> policyDtoList(){
		
		List<PolicyDto> response = new ArrayList<>();
		
		List<PolicyMstModel> policyMstList = policyRepo.findAllByOrderByCreateOnDesc();
		
		for(PolicyMstModel policyMstObject : policyMstList) {
			PolicyDto policyDtoObject = new PolicyDto();
			
			policyDtoObject.setPolicyId(policyMstObject.getPolicyId());
			policyDtoObject.setPolicyCode(policyMstObject.getPolicyCode());
			policyDtoObject.setPolicyName(policyMstObject.getName());
			String issueDate = managePolicyService.dateFormat(policyMstObject.getIssueDate());
			
			policyDtoObject.setCreationDate(issueDate);
			
			response.add(policyDtoObject);
		}
		return response;
		
	}

}
