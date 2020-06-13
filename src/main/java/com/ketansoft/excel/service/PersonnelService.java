package com.ketansoft.excel.service;

import com.ketansoft.excel.entity.PersonnelEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:03:11
 */
public interface PersonnelService {
	
	PersonnelEntity queryObject(Integer pId);
	
	List<PersonnelEntity> queryList(Map<String, Object> map);

	List<PersonnelEntity>sameList(Map<String,Object>map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PersonnelEntity personnel);

	void saveBatch(List<PersonnelEntity> personnelList);

	void update(PersonnelEntity personnel);
	
	void delete(Integer pId);
	
	void deleteBatch(Integer[] pIds);
}
