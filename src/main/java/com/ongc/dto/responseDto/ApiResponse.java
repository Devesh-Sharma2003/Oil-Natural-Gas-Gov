package com.ongc.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse<T> {
	
	private Boolean status;
	
	private String message;
	
	private Object result;
	
	private Integer statusCode;
	

//	public Boolean getStatus() {
//		return status;
//	}
//
//	public void setStatus(Boolean status) {
//		this.status = status;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public Object getResult() {
//		return result;
//	}
//
//	public void setResult(Object result) {
//		this.result = result;
//	}
//
//	public Integer getStatusCode() {
//		return statusCode;
//	}
//
//	public void setStatusCode(Integer statusCode) {
//		this.statusCode = statusCode;
//	}
	
	

}
