package com.ketansoft.excel.service;

import com.ketansoft.excel.entity.MajorDiseaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:47:28
 */
public interface MajorDiseaseService {
	
	MajorDiseaseEntity queryObject(Integer iId);
	
	List<MajorDiseaseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MajorDiseaseEntity majorDisease);

	void saveBatch(List<MajorDiseaseEntity> majorDiseaseList);

	void update(MajorDiseaseEntity majorDisease);
	
	void delete(Integer iId);
	
	void deleteBatch(Integer[] iIds);
}
