package com.ketansoft.excel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.ketansoft.excel.entity.MajorDiseaseEntity;
import com.ketansoft.excel.service.MajorDiseaseService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:47:28
 */
@Controller
@RequestMapping("majordisease")
public class MajorDiseaseController {
	@Autowired
	private MajorDiseaseService majorDiseaseService;
	
	@RequestMapping("/majordisease.html")
	public String list(){
		return "majordisease/majordisease.html";
	}
	
	@RequestMapping("/majordisease_add.html")
	public String add(){
		return "majordisease/majordisease_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("majordisease:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MajorDiseaseEntity> majorDiseaseList = majorDiseaseService.queryList(map);
		int total = majorDiseaseService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(majorDiseaseList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{iId}")
	@RequiresPermissions("majordisease:info")
	public R info(@PathVariable("iId") Integer iId){
		MajorDiseaseEntity majorDisease = majorDiseaseService.queryObject(iId);
		
		return R.ok().put("majorDisease", majorDisease);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("majordisease:save")
	public R save(@RequestBody MajorDiseaseEntity majorDisease){
		majorDiseaseService.save(majorDisease);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("majordisease:update")
	public R update(@RequestBody MajorDiseaseEntity majorDisease){
		majorDiseaseService.update(majorDisease);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("majordisease:delete")
	public R delete(@RequestBody Integer[] iIds){
		majorDiseaseService.deleteBatch(iIds);
		
		return R.ok();
	}
	
}
