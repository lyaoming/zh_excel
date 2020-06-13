package com.ketansoft.excel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.excel.mapper.PersonnelDao;
import com.ketansoft.excel.entity.PersonnelEntity;
import com.ketansoft.excel.service.PersonnelService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-08-20 15:03:11
 */
@Service("personnelService")
public class PersonnelServiceImpl implements PersonnelService {
	@Autowired
	private PersonnelDao personnelDao;
	
	@Override
	public PersonnelEntity queryObject(Integer pId){
		return personnelDao.queryObject(pId);
	}
	
	@Override
	public List<PersonnelEntity> queryList(Map<String, Object> map){
		return personnelDao.queryList(map);
	}

	@Override
	public List<PersonnelEntity> sameList(Map<String, Object> map){
		return personnelDao.sameList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return personnelDao.queryTotal(map);
	}
	
	@Override
	public void save(PersonnelEntity personnel){
		personnelDao.save(personnel);
	}

	@Override
	public void saveBatch(List<PersonnelEntity> personnelList){
		personnelDao.saveBatch(personnelList);
	}

	@Override
	public void update(PersonnelEntity personnel){
		personnelDao.update(personnel);
	}
	
	@Override
	public void delete(Integer pId){
		personnelDao.delete(pId);
	}
	
	@Override
	public void deleteBatch(Integer[] pIds){
		personnelDao.deleteBatch(pIds);
	}
	
}
