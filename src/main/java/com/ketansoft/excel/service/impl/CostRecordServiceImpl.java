package com.ketansoft.excel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.excel.mapper.CostRecordDao;
import com.ketansoft.excel.entity.CostRecordEntity;
import com.ketansoft.excel.service.CostRecordService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-08-20 17:12:33
 */
@Service("costRecordService")
public class CostRecordServiceImpl implements CostRecordService {
	@Autowired
	private CostRecordDao costRecordDao;
	
	@Override
	public CostRecordEntity queryObject(Integer cId){
		return costRecordDao.queryObject(cId);
	}
	
	@Override
	public List<CostRecordEntity> queryList(Map<String, Object> map){
		return costRecordDao.queryList(map);
	}

	@Override
	public List<CostRecordEntity> queryMa(Map<String, Object> map){
		return costRecordDao.queryMa(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return costRecordDao.queryTotal(map);
	}
	
	@Override
	public void save(CostRecordEntity costRecord){
		costRecordDao.save(costRecord);
	}

	@Override
	public void saveBatch(List<CostRecordEntity> costRecordList){
		costRecordDao.saveBatch(costRecordList);
	}

	@Override
	public void update(CostRecordEntity costRecord){
		costRecordDao.update(costRecord);
	}
	
	@Override
	public void delete(Integer cId){
		costRecordDao.delete(cId);
	}
	
	@Override
	public void deleteBatch(Integer[] cIds){
		costRecordDao.deleteBatch(cIds);
	}
	
}
