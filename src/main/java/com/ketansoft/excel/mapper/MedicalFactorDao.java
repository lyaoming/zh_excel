package com.ketansoft.excel.mapper;

import com.ketansoft.excel.entity.MedicalFactorEntity;
import java.util.List;
import com.framework.dao.BaseDao;
/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 16:14:20
 */
public interface MedicalFactorDao extends BaseDao<MedicalFactorEntity> {
    void saveBatch(List<MedicalFactorEntity> medicalFactorList);
}
