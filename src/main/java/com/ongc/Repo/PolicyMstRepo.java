package com.ongc.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ongc.dto.requestDto.SearchByFilterDto;
import com.ongc.model.PolicyMstModel;

@Repository
public interface PolicyMstRepo extends JpaRepository<PolicyMstModel, Long>{
	
	
	@Query(value="from PolicyMstModel p order by p.createdOn desc")
	List<PolicyMstModel> findAllByOrderByCreateOnDesc();
	
	
	@Query(value="select pm.policy_id policyId, pm.policy_code policyCode, pm.name policyName,"
			+ "pm.issue_date creationDate from policy_mst pm"
			+ "where (:policyName is NULL or pm.name=:policyName) AND (:domainId is NULL or"
			+ "pm.domain_id=CAST(:domainId AS INTEGER)) AND (:creationDate is NULL or pm.issue_date=TO_DATE(:creationDate ,'yyyy-MM-dd')) AND"
			+ " (:effectiveFrom is NULL or pm.effective_From>=TO_DATE(:effectiveFrom , 'yyyy-MM-dd')) AND (:effectiveTo is NULL or pm.effective_to<=TO_DATE(:effectiveTo, 'yyyy-MM-dd')) order by pm.created_on DESC", nativeQuery = true)
	List<SearchByFilterDto> findByCertainCond(
			@Param("policyName")String policyName,
			@Param("domainId")String domainId,
			@Param("effectiveFrom") String effectiveFrom,
			@Param("effectiveTo") String effectiveTo,
			@Param("creationDate") String creationDate);

}
