package com.ketansoft.excel.service;

import com.ketansoft.excel.entity.MedicalFactorEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 16:14:20
 */
public interface MedicalFactorService {
	
	MedicalFactorEntity queryObject(Integer dId);
	
	List<MedicalFactorEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MedicalFactorEntity medicalFactor);

	void saveBatch(List<MedicalFactorEntity> medicalFactorList);

	void update(MedicalFactorEntity medicalFactor);
	
	void delete(Integer dId);
	
	void deleteBatch(Integer[] dIds);
}
