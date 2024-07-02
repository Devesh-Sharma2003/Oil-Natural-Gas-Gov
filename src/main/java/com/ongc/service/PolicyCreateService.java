package com.ongc.service;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongc.Repo.AmendmentRepo;
import com.ongc.Repo.PolicyMstRepo;
import com.ongc.Repo.UserMstRepo;
import com.ongc.Repo.validityRepo;
import com.ongc.dto.requestDto.PolicyRqtDto;
import com.ongc.dto.responseDto.ApiResponse;
import com.ongc.dto.responseDto.FileDto;
import com.ongc.mappper.PolicyDtoToPolicyMstMapper;
import com.ongc.model.AmendmentMstModel;
import com.ongc.model.DocPolicyAmendMst;
import com.ongc.model.PolicyMstModel;
import com.ongc.model.UserMstModel;
import com.ongc.model.ValidityMstModel;

@Service
public class PolicyCreateService {
	
	@Autowired
	private AttachmentService attachService;
	
	@Autowired
	private PolicyMstRepo policyMstRepo;
	
	@Autowired
	private PolicyDtoToPolicyMstMapper policyDtoToPolicyMstMapper;
	
	private ValidityMstModel validityMstModel;

	@Autowired
	private validityRepo validityRepo;

	@Autowired
	private UserMstRepo userMstRepo;
	
	@Autowired
	private AmendmentRepo amendmentRepo;
	
	public ApiResponse<?> createPolicy(PolicyRqtDto policyRqtDto, Long userId){
		
		PolicyMstModel policyMstModel = policyDtoToPolicyMstMapper.policyDtoToModel(policyRqtDto);
		
		validityMstModel = this.validityRepo.getById(policyRqtDto.getValidId());
		if(policyRqtDto.getValidId()==1) {
			policyMstModel.setValidityMst(validityMstModel);
			policyMstModel.setEffectiveFrom(policyRqtDto.getEffectiveFrom());
			policyMstModel.setEffectiveTo(policyRqtDto.getEffectiveTo());
		}else {
			policyMstModel.setValidityMst(validityMstModel);
		}
		
		policyMstModel.setIssueDate(new Date());
		
		Optional<UserMstModel> userMstModel = this.userMstRepo.findById(userId);
		policyMstModel.setCreatedBy(userMstModel.get());
		
		policyMstRepo.save(policyMstModel);
		
		return new ApiResponse<>(true,"Details saved successfully", policyMstModel.getCode(),HttpStatus.OK.value());	
	}
	
	public ResponseEntity<?> savePolicyHandler(PolicyMstModel policyMstModel) throws FileNotFoundException{
		for(DocPolicyAmendMst i: policyMstModel.getDocPolicyAmendMst()) {
			i.setPolicy(policyMstModel);
			i.setCrtBy(policyMstModel.getCreatedBy());
			i.setFilePath(attachService
					.uploadOnRealPath(new FileDto(i.getFilePath(), String.valueOf(policyMstModel.getPolicyId()))));
			i.setActive(true);
		}
		policyMstModel.setActive(true);
		policyMstRepo.save(policyMstModel);
		
		return new ApiResponse(true, "Data saved successfully", policyMstModel.getPolicyCode(), HttpStatus.OK.value());
	}
	
	public ApiResponse<?> createAmendment(AmendmentMstModel aModel) throws FileNotFoundException{
		
		aModel.setActive(true);
		
		for(DocPolicyAmendMst i:aModel.getDocPolicyAmendMst()) {
			i.setAmendment(aModel);
			i.setCrtBy(aModel.getCreatedBy());
			i.setFilePath(attachService
					.uploadOnRealPath(new FileDto(i.getFilePath(), String.valueOf(aModel.getAmendmentId()))));
			i.setActive(true);
		}
		amendmentRepo.save(aModel);
		
		return new ApiResponse<>(true, "Data saved successfully", "", HttpStatus.OK.value()));
	}

}
