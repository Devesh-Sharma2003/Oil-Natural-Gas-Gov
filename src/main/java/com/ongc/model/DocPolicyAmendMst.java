package com.ongc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="doc_policy_amend")
public class DocPolicyAmendMst {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doc_id")
	private Long docId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="policy_id", referencedColumnName = "policy_id")
	private PolicyMstModel policyMstModel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="aid", referencedColumnName = "aid")
	private AmendmentMstModel amendment;
	
	@Column(name="filename")
	private String fileName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", referencedColumnName = "user_id")
	private UserMstModel crtBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updated_by", referencedColumnName = "user_id")
	private UserMstModel uptBy;
	
	@Column(name = "attachment_type")
	private String attachType;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "created_on")
	@CreationTimestamp
	private String crtOn;
	
	@Column(name = "upadated_on")
	@CreationTimestamp
	private String uptOn;

}
