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

import com.ketansoft.excel.entity.MedicalFactorEntity;
import com.ketansoft.excel.service.MedicalFactorService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 16:14:20
 */
@Controller
@RequestMapping("medicalfactor")
public class MedicalFactorController {
	@Autowired
	private MedicalFactorService medicalFactorService;
	
	@RequestMapping("/medicalfactor.html")
	public String list(){
		return "medicalfactor/medicalfactor.html";
	}
	
	@RequestMapping("/medicalfactor_add.html")
	public String add(){
		return "medicalfactor/medicalfactor_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if(page!=null&&limit!=null) {
			map.put("offset", (page - 1) * limit);
			map.put("limit", limit);
		}
		//查询列表数据
		List<MedicalFactorEntity> medicalFactorList = medicalFactorService.queryList(map);
		if(page!=null&&limit!=null) {
			int total = medicalFactorService.queryTotal(map);

			PageUtils pageUtil = new PageUtils(medicalFactorList, total, limit, page);

			return R.ok().put("page", pageUtil);
		}
		return R.ok().put("medicalFactorList",medicalFactorList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{dId}")
	@RequiresPermissions("medicalfactor:info")
	public R info(@PathVariable("dId") Integer dId){
		MedicalFactorEntity medicalFactor = medicalFactorService.queryObject(dId);
		
		return R.ok().put("medicalFactor", medicalFactor);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("medicalfactor:save")
	public R save(@RequestBody MedicalFactorEntity medicalFactor){
		medicalFactorService.save(medicalFactor);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("medicalfactor:update")
	public R update(@RequestBody MedicalFactorEntity medicalFactor){
		medicalFactorService.update(medicalFactor);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("medicalfactor:delete")
	public R delete(@RequestBody Integer[] dIds){
		medicalFactorService.deleteBatch(dIds);
		
		return R.ok();
	}
	
}
