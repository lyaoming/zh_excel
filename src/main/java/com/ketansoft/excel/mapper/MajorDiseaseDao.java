package com.ketansoft.excel.mapper;

import com.ketansoft.excel.entity.MajorDiseaseEntity;
import java.util.List;
import com.framework.dao.BaseDao;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:47:28
 */
public interface MajorDiseaseDao extends BaseDao<MajorDiseaseEntity> {
    void saveBatch(List<MajorDiseaseEntity> majorDiseaseList);
}
