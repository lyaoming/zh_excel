package com.ketansoft.excel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.excel.mapper.MedicalFactorDao;
import com.ketansoft.excel.entity.MedicalFactorEntity;
import com.ketansoft.excel.service.MedicalFactorService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-08-20 16:14:20
 */
@Service("medicalFactorService")
public class MedicalFactorServiceImpl implements MedicalFactorService {
	@Autowired
	private MedicalFactorDao medicalFactorDao;
	
	@Override
	public MedicalFactorEntity queryObject(Integer dId){
		return medicalFactorDao.queryObject(dId);
	}
	
	@Override
	public List<MedicalFactorEntity> queryList(Map<String, Object> map){
		return medicalFactorDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return medicalFactorDao.queryTotal(map);
	}
	
	@Override
	public void save(MedicalFactorEntity medicalFactor){
		medicalFactorDao.save(medicalFactor);
	}

	@Override
	public void saveBatch(List<MedicalFactorEntity> medicalFactorList){
		medicalFactorDao.saveBatch(medicalFactorList);
	}

	@Override
	public void update(MedicalFactorEntity medicalFactor){
		medicalFactorDao.update(medicalFactor);
	}
	
	@Override
	public void delete(Integer dId){
		medicalFactorDao.delete(dId);
	}
	
	@Override
	public void deleteBatch(Integer[] dIds){
		medicalFactorDao.deleteBatch(dIds);
	}
	
}
