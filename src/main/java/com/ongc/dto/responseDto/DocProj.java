package com.ongc.dto.responseDto;

import java.sql.Date;

public interface DocProj {
	
	String getFilePath();
	
	String getFileName();
	
	String attachType();
	
	Date getIssueDate();
	
	String getUploadedBy();

}
