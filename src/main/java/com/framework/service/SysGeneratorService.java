package com.framework.service;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年12月19日 下午3:33:38
 */
public interface SysGeneratorService {
	/**
	 * 查询所有表
	 */
	List<Map<String, Object>> queryList(Map<String, Object> map);

	/**
	 * 统计表的总数
	 */
	int queryTotal(Map<String, Object> map);

	/**
	 * 根据表名，查询表
	 */
	Map<String, String> queryTable(String tableName);

	/**
	 * 根据表名，查询列的列表
	 */
	List<Map<String, String>> queryColumns(String tableName);

	/**
	 * 生成代码
	 */
	byte[] generatorCode(String[] tableNames);
}
