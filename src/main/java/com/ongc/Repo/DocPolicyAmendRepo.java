package com.ongc.Repo;

import java.util.List;
import com.ongc.dto.responseDto.CommonDrpDwnDto;
import com.ongc.dto.responseDto.DocProj;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ongc.model.DocPolicyAmendMst;

public interface DocPolicyAmendRepo extends JpaRepository<DocPolicyAmendMst, Long>{
	
	@Query(value="select d.policy_id, p.name as name, a.aid as id from doc_policy_amend d join policy_mst p on p.policy_id = d.policy_id join amendment a on a.policy_id = p.policy_id "
			+ "group by a.aid,d.policy_id,p.name order by a.created_on desc limit 5", nativeQuery = true)
	List<CommonDrpDwnDto> getRecentAmendments();
	
	@Query(value="select d.fileName as fileName, d.filePath as fielPath, d.amendment.type as attachType,cast(d.amendment.issueDate as date) as issueDate, d.crtBy.fullName as uploadedBy from DocPolicyAmendMst d where d.amendment.amendmentId =:id")
	List<DocProj> findDocByAmendmentId(Long id);

}
