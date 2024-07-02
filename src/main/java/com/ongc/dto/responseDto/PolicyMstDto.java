package com.ongc.dto.responseDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyMstDto {
	
	private String code;
	
	private String domain;
	
	private String validity;
	
	private String effectiveFrom;
	
	private String effectiveTo;
	
	private String fullName;
	
	private String name;
	
	private String crtOn;
	
	private List<PolicyDocDto> docPolicyAmendMst;

}
