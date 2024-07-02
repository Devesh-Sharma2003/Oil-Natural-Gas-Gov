package com.ongc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ongc.Repo.DocPolicyAmendRepo;
import com.ongc.dto.responseDto.CommonDrpDwnDto;
import com.ongc.dto.responseDto.DocProj;

@Service
public class DashboardService {
	
	private DocPolicyAmendRepo dPolicyAmendRepo;
	
	public List<CommonDrpDwnDto> getAmendmentList(){
		List<CommonDrpDwnDto> policyAmendmentList = this.dPolicyAmendRepo.getRecentAmendments();
		
		return policyAmendmentList;
	}
	
	public List<DocProj> getAmendDocsBy(Long amendmentId){
		
		return dPolicyAmendRepo.findDocByAmendmentId(amendmentId);
	}

}
