package com.ketansoft.excel.mapper;

import com.ketansoft.excel.entity.PersonnelEntity;
import java.util.List;
import java.util.Map;
import com.framework.dao.BaseDao;
/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:03:11
 */
public interface PersonnelDao extends BaseDao<PersonnelEntity> {

    void saveBatch(List<PersonnelEntity> personnelList);

    List<PersonnelEntity>sameList(Map<String,Object>map);
}
