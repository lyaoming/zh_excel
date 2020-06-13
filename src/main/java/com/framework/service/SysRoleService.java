package com.framework.service;

import com.framework.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface SysRoleService {
	/**
	 * 根据角色ID，查询角色
	 */
	SysRoleEntity queryObject(Long roleId);

	/**
	 * 查询所有角色
	 */
	List<SysRoleEntity> queryList(Map<String, Object> map);

	/**
	 * 统计所有角色总数
	 */
	int queryTotal(Map<String, Object> map);

	/**
	 * 持久化角色
	 */
	void save(SysRoleEntity role);

	/**
	 * 更新角色
	 */
	void update(SysRoleEntity role);

	/**
	 * 批量删除角色
	 */
	void deleteBatch(Long[] roleIds);
}
