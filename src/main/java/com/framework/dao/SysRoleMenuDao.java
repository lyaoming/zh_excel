package com.framework.dao;

import com.framework.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色与菜单对应关系
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年9月18日 上午9:33:46
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
