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

import com.ketansoft.excel.entity.PersonnelStatusEntity;
import com.ketansoft.excel.service.PersonnelStatusService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:26:07
 */
@Controller
@RequestMapping("personnelstatus")
public class PersonnelStatusController {
	@Autowired
	private PersonnelStatusService personnelStatusService;
	
	@RequestMapping("/personnelstatus.html")
	public String list(){
		return "personnelstatus/personnelstatus.html";
	}
	
	@RequestMapping("/personnelstatus_add.html")
	public String add(){
		return "personnelstatus/personnelstatus_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("personnelstatus:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if(page!=null&&limit!=null) {
			map.put("offset", (page - 1) * limit);
			map.put("limit", limit);
		}
		
		//查询列表数据
		List<PersonnelStatusEntity> personnelStatusList = personnelStatusService.queryList(map);
		if(page!=null&&limit!=null) {
			int total = personnelStatusService.queryTotal(map);

			PageUtils pageUtil = new PageUtils(personnelStatusList, total, limit, page);

			return R.ok().put("page", pageUtil);
		}
		return R.ok().put("personnelStatusList",personnelStatusList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{sId}")
	@RequiresPermissions("personnelstatus:info")
	public R info(@PathVariable("sId") Integer sId){
		PersonnelStatusEntity personnelStatus = personnelStatusService.queryObject(sId);
		
		return R.ok().put("personnelStatus", personnelStatus);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("personnelstatus:save")
	public R save(@RequestBody PersonnelStatusEntity personnelStatus){
		personnelStatusService.save(personnelStatus);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("personnelstatus:update")
	public R update(@RequestBody PersonnelStatusEntity personnelStatus){
		personnelStatusService.update(personnelStatus);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("personnelstatus:delete")
	public R delete(@RequestBody Integer[] sIds){
		personnelStatusService.deleteBatch(sIds);
		
		return R.ok();
	}
	
}
