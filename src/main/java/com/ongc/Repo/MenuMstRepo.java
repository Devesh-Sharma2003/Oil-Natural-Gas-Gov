package com.ongc.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ongc.model.MenuMstModel;

public interface MenuMstRepo extends JpaRepository<MenuMstModel, Long>{
	
	@Query(nativeQuery=false, value="select m from MenuMstModel m where m.id=:i AND m.isActive=true AND m.parentMenuId is NULL")
	Optional<MenuMstModel> findByParentMenuConditions(Long i);
	
	@Query(nativeQuery = false, value = "select m from MenuMstModel m where m.parentMenuId=:i AND m.isActive=true")
	List<MenuMstModel> findBySubMenuConditions(Long i);

}
