package com.framework.dao;

import com.framework.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 用户与角色对应关系
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年9月18日 上午9:34:46
 */
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
