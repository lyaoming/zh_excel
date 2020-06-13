package com.framework.service.impl;

import com.framework.dao.SysUserRoleDao;
import com.framework.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系实现类
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年9月18日 上午9:45:48
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	/**
	 * 根据用户ID和角色ID列表，持久化用户与角色对应关系
	 */
	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if (roleIdList.size() == 0) {
			return;
		}

		// 先删除用户与角色关系
		sysUserRoleDao.delete(userId);

		// 保存用户与角色关系
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("roleIdList", roleIdList);
		sysUserRoleDao.save(map);
	}

	/**
	 * 根据用户ID，获取角色ID列表
	 */
	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return sysUserRoleDao.queryRoleIdList(userId);
	}

	/**
	 * 根据用户ID，删除对应关系
	 */
	@Override
	public void delete(Long userId) {
		sysUserRoleDao.delete(userId);
	}
}
