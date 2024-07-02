package com.ongc.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongc.dto.requestDto.PolicyRqtDto;
import com.ongc.dto.responseDto.ApiResponse;
import com.ongc.model.AmendmentMstModel;
import com.ongc.model.PolicyMstModel;
import com.ongc.service.PolicyCreateService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/documents")
public class SavePolicyController {
	
	@Autowired
	private PolicyCreateService policyCreateService;
	
	@PostMapping("/savePolicy")
	public ResponseEntity<?> savePolicy(@RequestBody PolicyMstModel policyMstModel) throws FileNotFoundException{
		return new ResponseEntity<>(policyCreateService.savePolicyHandler(policyMstModel),HttpStatus.OK);
	}
	
	@PostMapping("/createAmendment")
	public ResponseEntity<ApiResponse<?>> createAmendment(@RequestBody AmendmentMstModel aModel) throws FileNotFoundException{
		return new ResponseEntity<>(policyCreateService.createAmendment(aModel),HttpStatus.OK);
	}
	
	@PostMapping("/createPolicy/{userId}")
	public ResponseEntity<ApiResponse<?>> createPolicy(@RequestBody PolicyRqtDto policyRqtDto,
			@PathVariable("userId") Long userId) throws FileNotFoundException{
		return new ResponseEntity<>(policyCreateService.createPolicy(policyRqtDto, userId),HttpStatus.OK);
	}

}
