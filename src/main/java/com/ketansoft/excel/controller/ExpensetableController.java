package com.ketansoft.excel.controller;

import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.ketansoft.excel.entity.CostRecordEntity;
import com.ketansoft.excel.service.CostRecordService;
import com.ketansoft.excel.service.ExpensetableService;
import net.sf.json.JSONArray;
import org.apache.ibatis.logging.LogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletionService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
@Controller
@RequestMapping("expensetable")
public class ExpensetableController {
    @Autowired
    private CostRecordService costRecordService;

    @Autowired
    private ExpensetableService expensetableService;

    @RequestMapping("/expensetable.html")
    public String list(){
        return "expensetable/expensetable.html";
    }

    List<CostRecordEntity> exportList=new ArrayList<CostRecordEntity>();

    Long nowDate=new Date().getTime();

    SimpleDateFormat ydf=new SimpleDateFormat("yyyy");



    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public R list( Integer year,Integer pId, Integer page, Integer limit){

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("year",year);

        if(pId!=null) {
            map.put("pId", pId);
        }

        //查询列表数据
        List<CostRecordEntity> expenseList=expensetableService.queryList(map);

        exportList=expenseList;

        if(page!=null&&limit!=null) {

            int total = expensetableService.queryTotal(map);

            PageUtils pageUtil = new PageUtils(expenseList, total, limit, page);

            return R.ok().put("page", pageUtil);
        }
        return R.ok().put("expenseList",expenseList);
    }


    @ResponseBody
    @RequestMapping("/exportSelect")
    public R selectList(@RequestBody Integer[] cIds){
        exportList=new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("cIds",cIds);

        List<CostRecordEntity>expenseList=expensetableService.selectList(map);

        exportList=expenseList;

        return R.ok();
    }
    private static Logger logger = Logger.getLogger(LogException.class.getName());
    @ResponseBody
    @RequestMapping("/downloadExcel")
    public R exportReport(HttpServletResponse response, HttpServletRequest request)  {
        if(exportList.size()==0){return R.error("导出失败！导出内容为空");}
        try {
            expensetableService.exportCostReport(response,request,exportList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "excel导出错误", e);
            return R.error("失败");
        }
        return R.ok("成功");
    }


}
