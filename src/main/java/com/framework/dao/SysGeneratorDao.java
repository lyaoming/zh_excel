package com.framework.dao;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年12月19日 下午3:32:04
 */
public interface SysGeneratorDao {
	/**
	 * 获取代码列表
	 */
	List<Map<String, Object>> queryList(Map<String, Object> map);

	/**
	 * 获取代码列表总数
	 */
	int queryTotal(Map<String, Object> map);

	/**
	 * 根据表名，查询表
	 */
	Map<String, String> queryTable(String tableName);

	/**
	 * 根据表名，获取列名
	 */
	List<Map<String, String>> queryColumns(String tableName);
}
