package com.ketansoft.excel.mapper;

import com.ketansoft.excel.entity.CostRecordEntity;
import java.util.List;
import java.util.Map;

import com.framework.dao.BaseDao;
/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 17:12:33
 */
public interface CostRecordDao extends BaseDao<CostRecordEntity> {
    void saveBatch(List<CostRecordEntity> costRecordList);

    List<CostRecordEntity> queryMa(Map<String, Object> map);
}
