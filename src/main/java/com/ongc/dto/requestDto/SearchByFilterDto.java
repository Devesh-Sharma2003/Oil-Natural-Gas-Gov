package com.ongc.dto.requestDto;

import java.util.Date;

public interface SearchByFilterDto {
	
	Long getPolicyId();
	
	String getPolicyCode();
	
	String getPolicyName();
	
	Date getCreationDate();

}
