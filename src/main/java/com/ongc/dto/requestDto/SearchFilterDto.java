package com.ongc.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFilterDto {
	
	private String policyName;
	
	private String domainId;
	
	private String creationDate;
	
	private String effectiveFrom;
	
	private String effectiveTo;

}
