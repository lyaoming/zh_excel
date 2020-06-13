package com.ketansoft.excel.mapper;
import java.util.List;
import com.framework.dao.BaseDao;
import com.ketansoft.excel.entity.PersonnelStatusEntity;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:26:07
 */
public interface PersonnelStatusDao extends BaseDao<PersonnelStatusEntity> {
    void saveBatch(List<PersonnelStatusEntity> personnelStatusList);
}
