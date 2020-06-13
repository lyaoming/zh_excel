package com.framework.service;

import java.util.List;

/**
 * 用户与角色对应关系
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年9月18日 上午9:43:24
 */
public interface SysUserRoleService {
	/**
	 * 根据用户ID和角色ID列表，持久化用户与角色对应关系
	 */
	void saveOrUpdate(Long userId, List<Long> roleIdList);

	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);

	/**
	 * 根据用户ID，删除对应关系
	 */
	void delete(Long userId);
}
