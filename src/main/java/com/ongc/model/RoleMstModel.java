package com.ongc.model;

import java.util.Date;

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
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="role_mst")
public class RoleMstModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	private Long id;
	
	@Column(name="role")
	private String role;
	
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
}
