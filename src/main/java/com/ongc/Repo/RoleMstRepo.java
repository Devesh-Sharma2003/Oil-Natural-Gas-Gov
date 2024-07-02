package com.ongc.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ongc.model.RoleMstModel;

public interface RoleMstRepo extends JpaRepository<RoleMstModel, Long>{
	
	Optional<RoleMstModel> findById(Long roleId);

}
