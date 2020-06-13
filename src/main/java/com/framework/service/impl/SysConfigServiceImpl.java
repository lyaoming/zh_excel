package com.framework.service.impl;

import com.alibaba.fastjson.JSON;
import com.framework.dao.SysConfigDao;
import com.framework.entity.SysConfigEntity;
import com.framework.service.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息实现类
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年12月4日 下午6:49:01
 */
@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	private SysConfigDao sysConfigDao;

	/**
	 * 保存配置信息
	 */
	@Override
	public void save(SysConfigEntity config) {
		sysConfigDao.save(config);
	}

	/**
	 * 更新配置信息
	 */
	@Override
	public void update(SysConfigEntity config) {
		sysConfigDao.update(config);
	}

	/**
	 * 根据key，更新value
	 */
	@Override
	public void updateValueByKey(String key, String value) {
		sysConfigDao.updateValueByKey(key, value);
	}

	/**
	 * 删除配置信息
	 */
	@Override
	public void deleteBatch(Long[] ids) {
		sysConfigDao.deleteBatch(ids);
	}

	/**
	 * 获取List列表
	 */
	@Override
	public List<SysConfigEntity> queryList(Map<String, Object> map) {
		return sysConfigDao.queryList(map);
	}

	/**
	 * 获取总记录数
	 */
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysConfigDao.queryTotal(map);
	}

	/**
	 * 根据ID，获取系统配置信息
	 * @param id 配置信息ID
	 * @return
	 */
	@Override
	public SysConfigEntity queryObject(Long id) {
		return sysConfigDao.queryObject(id);
	}

	/**
	 * 根据key，获取配置的value值
	 * @param key key
	 * @param defaultValue 缺省值
	 */
	@Override
	public String getValue(String key, String defaultValue) {
		String value = sysConfigDao.queryByKey(key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * 根据key，获取value的Object对象
	 * @param key key
	 * @param clazz Object对象
	 */
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) throws Exception {
		String value = getValue(key, null);
		if (StringUtils.isNotBlank(value)) {
			return JSON.parseObject(value, clazz);
		}

		return clazz.newInstance();
	}
}
