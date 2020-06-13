package com.ketansoft.excel.service;

import com.ketansoft.excel.entity.PersonnelStatusEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:26:07
 */
public interface PersonnelStatusService {
	
	PersonnelStatusEntity queryObject(Integer sId);
	
	List<PersonnelStatusEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PersonnelStatusEntity personnelStatus);

	void saveBatch(List<PersonnelStatusEntity> personnelStatusList);

	void update(PersonnelStatusEntity personnelStatus);
	
	void delete(Integer sId);
	
	void deleteBatch(Integer[] sIds);
}
