package com.framework.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年9月18日 上午9:26:39
 */
public class SysMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	private Long menuId;

	/**
	 * 父菜单ID，一级菜单为0
	 */
	private Long parentId;

	/**
	 * 父菜单名称
	 */
	private String parentName;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单URL
	 */
	private String url;

	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	private String perms;

	/**
	 * 类型 0：目录 1：菜单 2：按钮
	 */
	private Integer type;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer orderNum;

	/**
	 * ztree属性
	 */
	private Boolean open;

	/**
	 * 列表
	 */
	private List<?> list;

	/**
	 * 设置：菜单ID
	 * @param menuId 菜单ID
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * 获取：菜单ID
	 * @return Long
	 */
	public Long getMenuId() {
		return menuId;
	}

	/**
	 * 设置：父菜单ID，一级菜单为0
	 * @param parentId 父菜单ID，一级菜单为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取：父菜单ID，一级菜单为0
	 * @return Long
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 设置：菜单名称
	 * @param name 菜单名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：菜单名称
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：菜单URL
	 * @param url 菜单URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取：菜单URL
	 * @return String
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 获取：授权(多个用逗号分隔，如：user:list,user:create)
	 * @return String
	 */
	public String getPerms() {
		return perms;
	}

	/**
	 * 设置：授权(多个用逗号分隔，如：user:list,user:create)
	 * @param perms 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	public void setPerms(String perms) {
		this.perms = perms;
	}

	/**
	 * 获取：类型 0：目录 1：菜单 2：按钮
	 * @return Integer
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置：类型 0：目录 1：菜单 2：按钮
	 * @param type 类型 0：目录 1：菜单 2：按钮
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 设置：菜单图标
	 * @param icon 菜单图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取：菜单图标
	 * @return String
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置：排序
	 * @param orderNum 排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取：排序
	 * @return Integer
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * 获取：列表
	 * @return List
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * 设置：列表
	 * @param list 列表
	 */
	public void setList(List<?> list) {
		this.list = list;
	}

	/**
	 * 获取：父菜单名称
	 * @return String
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * 设置：父菜单名称
	 * @param parentName 父菜单名称
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * 获取：ztree属性
	 * @return Boolean
	 */
	public Boolean getOpen() {
		return open;
	}

	/**
	 * 设置：ztree属性
	 * @param open ztree属性
	 */
	public void setOpen(Boolean open) {
		this.open = open;
	}
}
