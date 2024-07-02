package com.ongc.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDto {
	
	private Long policyId;
	
	private String policyName;
	
	private String policyCode;
	
	private String creationDate;
}
