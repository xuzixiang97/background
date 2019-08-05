package com.tempetek.business.example.roleandmenu.entity;

import com.tempetek.core.base.LongId;

public class RoleAndMenuInfo extends LongId {

	private static final long serialVersionUID = 1L;
	//角色id
	private Long roleId;
	//菜单id
	private Long menuId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}