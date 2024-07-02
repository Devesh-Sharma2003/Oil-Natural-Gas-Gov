package com.ongc.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ongc.model.UserRoleMappingModel;

public interface UserRoleRepo extends JpaRepository<UserRoleMappingModel, Long> {
	
	@Query(value="select ur.roleId.id from UserRoleMappingModel ur where ur.userId.id=:userId")
	Long findByUserId(Long id);
	
	List<UserRoleMappingModel> findByUserId_Id(Long id);
	

}
