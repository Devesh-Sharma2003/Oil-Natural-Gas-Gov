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
@Table(name="menu_mst")
public class MenuMstModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="menu_id")
	private Long menuId;
	
	@Column(name="menu_name")
	private String menuName;
	
	@Column(name="icon")
	private String icon;
	
	@Column(name="link")
	private String link;
	
	@Column(name="parent_menu_id")
	private Long parentMenuId;
	
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
