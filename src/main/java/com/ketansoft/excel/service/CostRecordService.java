package com.ketansoft.excel.service;

import com.ketansoft.excel.entity.CostRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 17:12:33
 */
public interface CostRecordService {
	
	CostRecordEntity queryObject(Integer cId);
	
	List<CostRecordEntity> queryList(Map<String, Object> map);

	List<CostRecordEntity> queryMa(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CostRecordEntity costRecord);

	void saveBatch(List<CostRecordEntity> costRecordList);

	void update(CostRecordEntity costRecord);
	
	void delete(Integer cId);
	
	void deleteBatch(Integer[] cIds);
}
