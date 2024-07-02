package com.ongc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ongc.Repo.PolicyMstRepo;
import com.ongc.dto.requestDto.PolicyRqtDto;
import com.ongc.dto.requestDto.SearchByFilterDto;
import com.ongc.dto.requestDto.SearchFilterDto;
import com.ongc.dto.responseDto.ApiResponse;
import com.ongc.dto.responseDto.PolicyDto;

@Service
public class SearchByFilterService {
	
	@Autowired
	private ParentPolicyUploadService ParentPolicyUploadService;
	
	@Autowired
	private PolicyMstRepo policyMstRepo; 
	
	@Autowired
	private ManagePolicyService managePolicyService;
	
	public ApiResponse<?> getPolicyBySearch(SearchFilterDto searchFilterDto){
		
		List<PolicyDto> policyResponseDto = new ArrayList<>();
		
		List<SearchByFilterDto> policyDtoList = this.policyMstRepo.findByCertainCond(searchFilterDto.getPolicyName(),
				searchFilterDto.getDomainId(), searchFilterDto.getEffectiveFrom(),
				searchFilterDto.getEffectiveTo(),searchFilterDto.getCreationDate());
		
		for(SearchByFilterDto i : policyDtoList) {
			PolicyDto policyDto = new PolicyDto();
			
			policyDto.setPolicyId(i.getPolicyId());
			policyDto.setPolicyCode(i.getPolicyCode());
			policyDto.setPolicyName(i.getPolicyName());
			String issueDate = managePolicyService.dateFormat(i.getCreationDate());
			policyDto.setCreationDate(issueDate);
			
			policyResponseDto.add(policyDto);
		}
		
		return new ApiResponse<>(true,"Details fetched successfully", policyResponseDto, HttpStatus.OK.value());	
	}

}
