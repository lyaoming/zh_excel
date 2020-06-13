package com.framework.service.impl;

import com.framework.dao.SysMenuDao;
import com.framework.entity.SysMenuEntity;
import com.framework.service.SysMenuService;
import com.framework.service.SysUserService;
import com.framework.utils.Constant.MenuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理实现类
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年9月18日 上午9:42:16
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysUserService sysUserService;
	//@Autowired
	//private SysRoleMenuService sysRoleMenuService;

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList 用户菜单ID
	 */
	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuEntity> menuList = sysMenuDao.queryListParentId(parentId);
		if (menuIdList == null) {
			return menuList;
		}

		List<SysMenuEntity> userMenuList = new ArrayList<SysMenuEntity>();
		for (SysMenuEntity menu : menuList) {
			if (menuIdList.contains(menu.getMenuId())) {
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	/**
	 * 获取不包含按钮的菜单列表
	 */
	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return sysMenuDao.queryNotButtonList();
	}

	/**
	 * 获取用户菜单列表
	 */
	@Override
	public List<SysMenuEntity> getUserMenuList(Long userId) {
		// 系统管理员，拥有最高权限
		if (userId == 1) {
			return getAllMenuList(null);
		}

		// 用户菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}

	/**
	 * 查询菜单
	 */
	@Override
	public SysMenuEntity queryObject(Long menuId) {
		return sysMenuDao.queryObject(menuId);
	}

	/**
	 * 查询菜单列表
	 */
	@Override
	public List<SysMenuEntity> queryList(Map<String, Object> map) {
		return sysMenuDao.queryList(map);
	}

	/**
	 * 查询总数
	 */
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysMenuDao.queryTotal(map);
	}

	/**
	 * 保存菜单
	 */
	@Override
	public void save(SysMenuEntity menu) {
		sysMenuDao.save(menu);
	}

	/**
	 * 修改
	 */
	@Override
	public void update(SysMenuEntity menu) {
		sysMenuDao.update(menu);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteBatch(Long[] menuIds) {
		sysMenuDao.deleteBatch(menuIds);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
		// 查询根菜单列表
		List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
		// 递归获取子菜单
		getMenuTreeList(menuList, menuIdList);

		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
		List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

		for (SysMenuEntity entity : menuList) {
			if (entity.getType() == MenuType.CATALOG.getValue()) {// 目录
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}

		return subMenuList;
	}
}
