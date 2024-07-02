package com.ongc.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDocDto {
	
	private String fileName;
	
	private String filePath;
	
	private String attachType;
	
	private String amendType;
	
	private String amendDate;
	
	private String uploadedBy;

}
