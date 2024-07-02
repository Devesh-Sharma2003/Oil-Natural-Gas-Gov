package com.ongc.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="role_menu_map")
public class RoleMenuMappingModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="role_id", referencedColumnName = "role_id")
	private RoleMstModel role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="menu_id", referencedColumnName = "menu_id")
	private MenuMstModel menu;
	

}
