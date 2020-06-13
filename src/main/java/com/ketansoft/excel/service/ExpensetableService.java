package com.ketansoft.excel.service;
import com.ketansoft.excel.entity.CostRecordEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
public interface ExpensetableService {

    List<CostRecordEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);


    List<CostRecordEntity>selectList(Map<String, Object> map);

    /**
     * 导出收费报表
     * @param response
     * @param request
     */
    void exportCostReport(HttpServletResponse response, HttpServletRequest request, List exportList) throws Exception;

}
