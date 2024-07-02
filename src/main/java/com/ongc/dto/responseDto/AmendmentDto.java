package com.ongc.dto.responseDto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmendmentDto {

	private Long id;
	
	private String type;
	
	private String issueDate;
	
	private Timestamp updatedOn;
	
	private String updatedBy;
	
	private List<PolicyDocDto> policyDoc;
}
