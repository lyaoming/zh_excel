package com.framework.service.impl;

import com.framework.dao.SysRoleDao;
import com.framework.entity.SysRoleEntity;
import com.framework.service.SysRoleMenuService;
import com.framework.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色实现类
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	//@Autowired
	//private SysUserRoleService sysUserRoleService;

	/**
	 * 根据角色ID，查询角色
	 */
	@Override
	public SysRoleEntity queryObject(Long roleId) {
		return sysRoleDao.queryObject(roleId);
	}

	/**
	 * 查询所有角色
	 */
	@Override
	public List<SysRoleEntity> queryList(Map<String, Object> map) {
		return sysRoleDao.queryList(map);
	}

	/**
	 * 统计所有角色总数
	 */
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysRoleDao.queryTotal(map);
	}

	/**
	 * 持久化角色
	 */
	@Override
	@Transactional
	public void save(SysRoleEntity role) {
		role.setCreateTime(new Date());
		sysRoleDao.save(role);

		// 保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	/**
	 * 更新角色
	 */
	@Override
	@Transactional
	public void update(SysRoleEntity role) {
		sysRoleDao.update(role);

		// 更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	/**
	 * 批量删除角色
	 */
	@Override
	@Transactional
	public void deleteBatch(Long[] roleIds) {
		sysRoleDao.deleteBatch(roleIds);
	}
}
