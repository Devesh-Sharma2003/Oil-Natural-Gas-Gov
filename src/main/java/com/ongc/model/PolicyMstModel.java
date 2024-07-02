package com.ongc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="policy_mst")
public class PolicyMstModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policyId;
	
	@Column(name="policy_code", nullable=false, unique=true)
	private String policyCode;
	
	@Column(name="policy_code")
	private String policyName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="domain_id", referencedColumnName = "domain_id")
	private DomainMstModel domainMst;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="validity_id", referencedColumnName = "validity_id")
	private ValidityMstModel validityMst;
	
	@Column(name="effective_to")
	private Date effectiveTo;
	
	@Column(name="effective_from")
	private Date effectiveFrom;
	
	@Column(name="issue_date")
	private Date issueDate;
	
	@Column(name="created_on")
	@CreationTimestamp
	private Date crtOn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="created_by", referencedColumnName = "user_id")
	private Date crtBy;
	
	@Column(name="upadted_on")
	@UpdateTimestamp
	private Date uptOn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by", referencedColumnName = "user_id")
	private Date uptBy;
	
	@OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
	@JoinColumn(name="created_by", referencedColumnName = "user_id")
	private List<DocPolicyAmendMst> docPolicyAmendMst;
	
	
	

}
