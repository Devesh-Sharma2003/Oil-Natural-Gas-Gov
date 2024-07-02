package com.ongc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ongc.Repo.UserMstRepo;
import com.ongc.model.UserMstModel;

@Component
public class Util {
	
	@Autowired
	private UserMstRepo userMstRepo;
	
	public UserMstModel fetchUserDataByToken(Authentication authentication) {
		if(authentication!=null && authentication.isAuthenticated()) {
			UserMstModel authenticationUser = this.userMstRepo.findByUserName(authentication.getName());
			
			if(authenticationUser == null) {
				throw new SecurityException("Unauthorized access");
			}
			
			return authenticationUser;
		}else {
			throw new SecurityException("Unauthorized access");
		}
	}
}
