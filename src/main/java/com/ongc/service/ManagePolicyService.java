package com.ongc.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ongc.Repo.AmendmentRepo;
import com.ongc.Repo.DocPolicyAmendRepo;
import com.ongc.Repo.DomainRepo;
import com.ongc.Repo.PolicyMstRepo;
import com.ongc.Repo.UserMstRepo;
import com.ongc.Repo.validityRepo;
import com.ongc.dto.responseDto.AmendmentDto;
import com.ongc.dto.responseDto.PolicyDocDto;
import com.ongc.dto.responseDto.PolicyMstDto;
import com.ongc.model.DocPolicyAmendMst;

@Service
public class ManagePolicyService {
	
	@Autowired
	private PolicyMstRepo policyRepo;
	
	@Autowired
	private DomainRepo domainRepo;
	
	@Autowired
	private validityRepo validityRepo;
	
	@Autowired
	private UserMstRepo userMstRepo;
	
	@Autowired
	private AmendmentRepo amendmentRepo;
	
	@Autowired
	private DocPolicyAmendRepo docPolicyAmendRepo;
	
	public PolicyMstDto policyFromPolicyCode(Long policyId) {
		
		PolicyMstDto policyMstDto = new PolicyMstDto();
//		code for extracting PolicyMstModel from policyId.......it's easy man!
		
//		for extracting baseDocList
		
		List<PolicyDocDto> baseDocList = new ArrayList<>();
		
		PolicyDocDto pdoc;
		
		for(DocPolicyAmendMst doc:policyMstObject.get().getDocPolicyAmendMst()) {
			pdoc = new PolicyDocDto();
			
			pdoc.setFileName(doc.getFileName());
			pdoc.setFilePath(doc.getFilePath());
			pdoc.setAttachType(doc.getAttachType());
			pdoc.setFileUploadedBy(doc.getcrtBy().getUserName());
			
			if(doc.getAmendment()!=null) {
				pdoc.setAmendmentType(doc.getAmendment().getType());
				pdoc.setAmendmentDate(doc.getAmendment().getIssuedate());
			}
			baseDocList.add(pdoc);
		}
		
		policyMstDto.setDocPolicyAmendMst(baseDocList);
		return policyMstDto;	
	}
	
	public List<AmendmentDto> amendmentsFromPolicyCode(String policyCode){
		
//		code for extracting AmendMstModel from policyCode.......it's easy man!
		
		
		
		
	}
	
	public final String dateFormat(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat();
		return formatter.format(date);
	}
	
}
