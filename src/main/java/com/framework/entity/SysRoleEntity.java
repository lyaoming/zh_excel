package com.framework.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年9月18日 上午9:27:38
 */
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 菜单ID列表
	 */
	private List<Long> menuIdList;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 设置：角色ID
	 * @param roleId
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取：角色ID
	 * @return Long
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * 设置：角色名称
	 * @param roleName 角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 获取：角色名称
	 * @return String
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置：备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：备注
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 获取：创建时间
	 * @return Date
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：菜单ID列表
	 * @return Date
	 */
	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	/**
	 * 设置：菜单ID列表
	 * @param menuIdList 菜单ID列表
	 */
	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

}
