package com.ongc.dto.requestDto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyRqtDto {
	
	private String policyCode;
	
	private String policyName;
	
	private Long domainId;
	
	private Date creationDate;
	
	private Long validId;
	
	private Date effectiveFrom;
	
	private Date effectiveTo;
}
