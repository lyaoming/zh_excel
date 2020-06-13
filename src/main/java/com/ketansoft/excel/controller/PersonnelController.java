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

import com.ketansoft.excel.entity.PersonnelEntity;
import com.ketansoft.excel.service.PersonnelService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:03:11
 */
@Controller
@RequestMapping("personnel")
public class PersonnelController {
	@Autowired
	private PersonnelService personnelService;
	
	@RequestMapping("/personnel.html")
	public String list(){
		return "personnel/personnel.html";
	}
	
	@RequestMapping("/personnel_add.html")
	public String add(){
		return "personnel/personnel_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("personnel:list")
	public R list(String pName,Integer pStatus,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pName",pName);
		map.put("pStatus",pStatus);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<PersonnelEntity> personnelList = personnelService.queryList(map);
		int total = personnelService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(personnelList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{pId}")
	@RequiresPermissions("personnel:info")
	public R info(@PathVariable("pId") Integer pId){
		PersonnelEntity personnel = personnelService.queryObject(pId);
		
		return R.ok().put("personnel", personnel);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	public R save(@RequestBody PersonnelEntity personnel){

		Integer pId=null;

		Map<String,Object>map=new HashMap<>();

		map.put("pName",personnel.getPName());
		map.put("pNumber",personnel.getpNumber());

		List<PersonnelEntity>personnelSame=personnelService.sameList(map);

		if(personnelSame.size()>0){
			return R.error("该人员已存在！");
		}

		personnelService.save(personnel);

		List<PersonnelEntity>personnelList=personnelService.sameList(map);

		for(int i=0;i<personnelList.size();i++){
			PersonnelEntity personnelEntity=personnelList.get(i);
			pId=personnelEntity.getPId();
		}
		return R.ok().put("pId",pId);
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("personnel:update")
	public R update(@RequestBody PersonnelEntity personnel){
		personnelService.update(personnel);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("personnel:delete")
	public R delete(@RequestBody Integer[] pIds){
		personnelService.deleteBatch(pIds);
		
		return R.ok();
	}
	
}
