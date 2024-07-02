package com.ongc.dto.requestDto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtRequest implements Serializable{
	private static final long serialVersionUID = 5926468583005150707L; 
	
	private String username;
	
	private String password;

	private String password2;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
