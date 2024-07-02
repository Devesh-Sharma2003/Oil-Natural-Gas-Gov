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
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="amendment")
public class AmendmentMstModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="aid")
	private Long amendmentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="policy_id", referencedColumnName = "policy_id")
	private PolicyMstModel policyMstModel;
	
	@Column(name = "type")
	private String type;
	
	@Column(name="issue_date")
	private Date issueDate;
	
	@OneToMany(mappedBy = "amendment", cascade = CascadeType.ALL)
	@Where(clause = "is_active=true")
	private List<DocPolicyAmendMst> docPolicyAmendMst;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", referencedColumnName = "user_id")
	private UserMstModel crtBy;
	
	@Column(name = "created_on")
	@CreationTimestamp
	private String crtOn;
	
	@Column(name="upadted_on")
	@UpdateTimestamp
	private Date uptOn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by", referencedColumnName = "user_id")
	private Date uptBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="deleted_by", referencedColumnName = "user_id")
	private UserMstModel deletedBy;
	
	@Column(name = "deleted_on")
	private Date deletedOn;
	
}
