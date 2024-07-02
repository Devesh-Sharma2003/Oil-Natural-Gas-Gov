package com.ongc.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuMstModelDto {
	private Long menuId;
	private String menuName;
	private Long roleId;
	private String icon;
	private String link;
	private Long parentMenuId;

}
