package com.ongc.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ongc.model.UserMstModel;

public interface UserMstRepo extends JpaRepository<UserMstModel, Long>{
	
	UserMstModel findByUserName(String username);

}
