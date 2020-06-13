package com.ketansoft.excel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.excel.mapper.PersonnelStatusDao;
import com.ketansoft.excel.entity.PersonnelStatusEntity;
import com.ketansoft.excel.service.PersonnelStatusService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-08-20 15:26:07
 */
@Service("personnelStatusService")
public class PersonnelStatusServiceImpl implements PersonnelStatusService {
	@Autowired
	private PersonnelStatusDao personnelStatusDao;
	
	@Override
	public PersonnelStatusEntity queryObject(Integer sId){
		return personnelStatusDao.queryObject(sId);
	}
	
	@Override
	public List<PersonnelStatusEntity> queryList(Map<String, Object> map){
		return personnelStatusDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return personnelStatusDao.queryTotal(map);
	}
	
	@Override
	public void save(PersonnelStatusEntity personnelStatus){
		personnelStatusDao.save(personnelStatus);
	}

	@Override
	public void saveBatch(List<PersonnelStatusEntity> personnelStatusList){
		personnelStatusDao.saveBatch(personnelStatusList);
	}

	@Override
	public void update(PersonnelStatusEntity personnelStatus){
		personnelStatusDao.update(personnelStatus);
	}
	
	@Override
	public void delete(Integer sId){
		personnelStatusDao.delete(sId);
	}
	
	@Override
	public void deleteBatch(Integer[] sIds){
		personnelStatusDao.deleteBatch(sIds);
	}
	
}
