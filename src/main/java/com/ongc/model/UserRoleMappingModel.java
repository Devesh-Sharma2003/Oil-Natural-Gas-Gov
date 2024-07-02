package com.ongc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_role_map")
public class UserRoleMappingModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_role_id")
	private Long userRoleId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName = "user_id")
	private UserMstModel user;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="role_id", referencedColumnName = "role_id")
	private RoleMstModel role;
	
}
