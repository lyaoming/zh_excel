package com.ketansoft.excel.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.excel.entity.CostRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
public interface ExpensetableDao extends BaseDao<CostRecordEntity> {


    List<CostRecordEntity>selectList(Map<String, Object> map);

}
