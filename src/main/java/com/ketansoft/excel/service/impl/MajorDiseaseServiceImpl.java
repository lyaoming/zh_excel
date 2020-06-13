package com.ketansoft.excel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.excel.mapper.MajorDiseaseDao;
import com.ketansoft.excel.entity.MajorDiseaseEntity;
import com.ketansoft.excel.service.MajorDiseaseService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-08-20 15:47:28
 */
@Service("majorDiseaseService")
public class MajorDiseaseServiceImpl implements MajorDiseaseService {
	@Autowired
	private MajorDiseaseDao majorDiseaseDao;
	
	@Override
	public MajorDiseaseEntity queryObject(Integer iId){
		return majorDiseaseDao.queryObject(iId);
	}
	
	@Override
	public List<MajorDiseaseEntity> queryList(Map<String, Object> map){
		return majorDiseaseDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return majorDiseaseDao.queryTotal(map);
	}
	
	@Override
	public void save(MajorDiseaseEntity majorDisease){
		majorDiseaseDao.save(majorDisease);
	}

	@Override
	public void saveBatch(List<MajorDiseaseEntity> majorDiseaseList){
		majorDiseaseDao.saveBatch(majorDiseaseList);
	}

	@Override
	public void update(MajorDiseaseEntity majorDisease){
		majorDiseaseDao.update(majorDisease);
	}
	
	@Override
	public void delete(Integer iId){
		majorDiseaseDao.delete(iId);
	}
	
	@Override
	public void deleteBatch(Integer[] iIds){
		majorDiseaseDao.deleteBatch(iIds);
	}
	
}
