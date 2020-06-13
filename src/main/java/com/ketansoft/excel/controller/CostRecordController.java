package com.ketansoft.excel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ketansoft.excel.entity.MajorDiseaseEntity;
import com.ketansoft.excel.entity.MedicalFactorEntity;
import com.ketansoft.excel.service.MajorDiseaseService;
import com.ketansoft.excel.service.MedicalFactorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jaxen.function.CountFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.ketansoft.excel.entity.CostRecordEntity;
import com.ketansoft.excel.service.CostRecordService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 17:12:33
 */
@Controller
@RequestMapping("costrecord")
public class CostRecordController {
	@Autowired
	private CostRecordService costRecordService;

	@Autowired
	private MedicalFactorService medicalFactorService;

	@Autowired
	private MajorDiseaseService majorDiseaseService;
	
	@RequestMapping("/costrecord.html")
	public String list(){
		return "costrecord/costrecord.html";
	}
	
	@RequestMapping("/costrecord_add.html")
	public String add(){
		return "costrecord/costrecord_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("costrecord:list")
	public R list(String pName,Integer classion,Integer year,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pName",pName);
		map.put("classion",classion);
		map.put("year",year);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CostRecordEntity> costRecordList = costRecordService.queryList(map);
		int total = costRecordService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(costRecordList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{cId}")
	@RequiresPermissions("costrecord:info")
	public R info(@PathVariable("cId") Integer cId){
		CostRecordEntity costRecord = costRecordService.queryObject(cId);
		return R.ok().put("costRecord", costRecord);
	}

	@ResponseBody
	@RequestMapping("/querylast/{cId}")
	public R queryLast(@PathVariable("cId") Integer cId){
		Map<String,Object>map=new HashMap<>();
		CostRecordEntity costRecord = costRecordService.queryObject(cId);
		map.put("pId",costRecord.getPId());
		List<CostRecordEntity> pevMa = costRecordService.queryMa(map);
		if(pevMa!=null&&pevMa.size()>0) {
			CostRecordEntity costRecordEntity = pevMa.get(pevMa.size() - 1);
			if (cId != costRecordEntity.getCId()) {
				return R.error("只能对最新记录进行修改！");
			}
		}
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	public R save(@RequestBody CostRecordEntity costRecord){
		Map<String,Object>map=new HashMap<>();

		MedicalFactorEntity medicalFactorEntity = medicalFactorService.queryObject(costRecord.getClassion());

		map.put("pId",costRecord.getPId());

		List<MajorDiseaseEntity> ma = majorDiseaseService.queryList(map);

		List<CostRecordEntity> pevMa = costRecordService.queryMa(map);


		int last = 0;

		if (pevMa.size() > 0) {
			last = pevMa.size() - 1;
		}


		if(costRecord.getSelfCost()!=null&&costRecord.getAllSelfcost()!=null&&costRecord.getOutMacount()==null) {


			//补充医疗系数
			costRecord.setMFactor(medicalFactorEntity.getDFactor());

			//补充医疗基数
			costRecord.setBaseNum(costRecord.getAllSelfcost() - costRecord.getSelfCost());

			//补充医疗报销额
			costRecord.setMCost(costRecord.getMFactor() * costRecord.getBaseNum());

			//补充医疗不报销额
			costRecord.setMNocost(costRecord.getBaseNum() - costRecord.getMCost());

			//重疾自付基数
			costRecord.setMaSelf(costRecord.getSelfCost() + costRecord.getMNocost());

			//上次重疾累计
			if(costRecord.getPevMa()!=null) {
				if (pevMa != null && pevMa.size() > 0) {

					CostRecordEntity costRecordEntity = pevMa.get(last);

					costRecord.setPevMa(costRecordEntity.getNowMa());

				} else {
					costRecord.setPevMa(0.00);
				}
			}
			//本次重疾累计
			costRecord.setNowMa(costRecord.getPevMa() + costRecord.getMaSelf());

		   //调用函数计算重疾报销额
			costRecord.setMaReduce(CountFunction(costRecord));

			//合计报销
			costRecord.setCostAll(costRecord.getMCost() + costRecord.getMaReduce());

			//实际支付
			costRecord.setAcCost(costRecord.getSelfCost() + costRecord.getMNocost() - costRecord.getMaReduce());

			costRecordService.save(costRecord);

			return R.ok();

		}else if(costRecord.getOutMacount()!=null){

			//重疾自付基数
			costRecord.setMaSelf(costRecord.getOutMacount());

			//个人缴付
			costRecord.setAllSelfcost(0.00);

			//自付
			costRecord.setSelfCost(0.00);

			//补充医疗系数
			costRecord.setMFactor(medicalFactorEntity.getDFactor());

			//补充医疗基数
			costRecord.setBaseNum(0.00);

			//补充医疗报销额
			costRecord.setMCost(0.00);

			//补充医疗不报销额
			costRecord.setMNocost(0.00);

			//上次重疾累计
			if(costRecord.getPevMa()==null) {
				if (pevMa != null && pevMa.size() > 0) {

					CostRecordEntity costRecordEntity = pevMa.get(last);

					costRecord.setPevMa(costRecordEntity.getNowMa());

				} else {
					costRecord.setPevMa(0.00);
				}
			}

			//本次重疾累计
			costRecord.setNowMa(costRecord.getPevMa() + costRecord.getMaSelf());

			//累进制报销算法计算报销额
			costRecord.setMaReduce(CountFunction(costRecord));


            //合计报销
			costRecord.setCostAll(costRecord.getMCost() + costRecord.getMaReduce());

			//实际支付
			costRecord.setAcCost(costRecord.getMaSelf() - costRecord.getMaReduce());

			costRecordService.save(costRecord);

			return R.ok();

		}
		return R.error("请输入计算数据！");
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update(@RequestBody CostRecordEntity costRecord){
		Map<String,Object>map=new HashMap<>();

		MedicalFactorEntity medicalFactorEntity = medicalFactorService.queryObject(costRecord.getClassion());

		map.put("pId",costRecord.getPId());

		List<MajorDiseaseEntity> ma = majorDiseaseService.queryList(map);

		List<CostRecordEntity> pevMa = costRecordService.queryMa(map);


		int last = 0;

		if (pevMa.size() > 1) {
			last = pevMa.size() -2;
		}


		if(costRecord.getSelfCost()!=null&&costRecord.getAllSelfcost()!=null&&costRecord.getOutMacount()==null) {

			//补充医疗系数
			costRecord.setMFactor(medicalFactorEntity.getDFactor());

			//补充医疗基数
			costRecord.setBaseNum(costRecord.getAllSelfcost() - costRecord.getSelfCost());

			//补充医疗报销额
			costRecord.setMCost(costRecord.getMFactor() * costRecord.getBaseNum());

			//补充医疗不报销额
			costRecord.setMNocost(costRecord.getBaseNum() - costRecord.getMCost());

			//重疾自付基数
			costRecord.setMaSelf(costRecord.getSelfCost() + costRecord.getMNocost());

			//上次重疾累计
			if(costRecord.getPevMa()==null) {
				if (pevMa != null && pevMa.size() > 0) {

					CostRecordEntity costRecordEntity = pevMa.get(last);

					costRecord.setPevMa(costRecordEntity.getNowMa());

				} else {
					costRecord.setPevMa(0.00);
				}
			}

			//本次重疾累计
			costRecord.setNowMa(costRecord.getPevMa() + costRecord.getMaSelf());

			//调用函数计算重疾报销额
			costRecord.setMaReduce(CountFunction(costRecord));

			//合计报销
			costRecord.setCostAll(costRecord.getMCost() + costRecord.getMaReduce());

			//实际支付
			costRecord.setAcCost(costRecord.getSelfCost() + costRecord.getMNocost() - costRecord.getMaReduce());

			costRecordService.update(costRecord);

			return R.ok();

		}else if(costRecord.getOutMacount()!=null){

			//重疾自付基数
			costRecord.setMaSelf(costRecord.getOutMacount());

			//个人缴付
			costRecord.setAllSelfcost(0.00);

			//自付
			costRecord.setSelfCost(0.00);

			//补充医疗系数
			costRecord.setMFactor(medicalFactorEntity.getDFactor());

			//补充医疗基数
			costRecord.setBaseNum(0.00);

			//补充医疗报销额
			costRecord.setMCost(0.00);

			//补充医疗不报销额
			costRecord.setMNocost(0.00);

			//上次重疾累计
			if(costRecord.getPevMa()==null) {
				if (pevMa != null && pevMa.size() > 0) {

					CostRecordEntity costRecordEntity = pevMa.get(last);

					costRecord.setPevMa(costRecordEntity.getNowMa());

				} else {
					costRecord.setPevMa(0.00);
				}
			}

			//本次重疾累计
			costRecord.setNowMa(costRecord.getPevMa() + costRecord.getMaSelf());

			//累进制报销算法计算报销额
			costRecord.setMaReduce(CountFunction(costRecord));

			//合计报销
			costRecord.setCostAll(costRecord.getMCost() + costRecord.getMaReduce());

			//实际支付
			costRecord.setAcCost(costRecord.getMaSelf() - costRecord.getMaReduce());

			costRecordService.update(costRecord);

			return R.ok();

		}
		return R.error("请输入计算数据！");

	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] cIds){
		costRecordService.deleteBatch(cIds);
		
		return R.ok();
	}

	public Double CountFunction(CostRecordEntity costRecord) {

		Map<String, Object> map = new HashMap<>();

		List<MajorDiseaseEntity> ma = majorDiseaseService.queryList(map);

		//累进制报销算法

		Double result = 0.00;

		int pevNum = 0;

		int nowNum = 0;

		int lastSize = 0;
		if (ma.size() >= 1) {
			lastSize = ma.size() - 1;
		}

		int len = 0;

		MajorDiseaseEntity majorDiseaseEntity0 = ma.get(lastSize);

		if (majorDiseaseEntity0!=null&&costRecord.getPevMa() <= majorDiseaseEntity0.getMax()) {

			for (int i = 0; i < ma.size(); i++) {
				MajorDiseaseEntity majorDiseaseEntity = ma.get(i);
				if (majorDiseaseEntity.getMin() <= costRecord.getPevMa() && costRecord.getPevMa() <= majorDiseaseEntity.getMax()) {
					pevNum = i;
					break;
				}
			}
			for (int i = 0; i < ma.size(); i++) {
				MajorDiseaseEntity majorDiseaseEntity = ma.get(i);
				len = i + 1;
				if (majorDiseaseEntity.getMin() <= costRecord.getNowMa() && costRecord.getNowMa() <= majorDiseaseEntity.getMax()) {
					nowNum = i;
					len=i;
					break;
				}
			}
			if (len == ma.size()) {
				MajorDiseaseEntity majorDiseaseEntity = ma.get(len-1);
				costRecord.setNowMa(majorDiseaseEntity.getMax());
				nowNum = len - 1;
			}
			if (pevNum == nowNum) {
				MajorDiseaseEntity majorDiseaseEntity = ma.get(pevNum);
				result = result + costRecord.getMaSelf() * majorDiseaseEntity.getIFactor();
			}
			if (nowNum - pevNum == 1) {
				MajorDiseaseEntity majorDiseaseEntity1 = ma.get(pevNum);
				MajorDiseaseEntity majorDiseaseEntity2 = ma.get(nowNum);
				result = result + (majorDiseaseEntity1.getMax() - costRecord.getPevMa()) * majorDiseaseEntity1.getIFactor() + (costRecord.getNowMa() - majorDiseaseEntity1.getMax()) * majorDiseaseEntity2.getIFactor();
			}
			if (nowNum - pevNum > 1) {
				Double last = costRecord.getMaSelf();
				//OK
				for (int i = pevNum + 1; i < nowNum; i++) {
					MajorDiseaseEntity majorDiseaseEntity = ma.get(i);
					result = result + ((majorDiseaseEntity.getMax() - majorDiseaseEntity.getMin()) * majorDiseaseEntity.getIFactor());
					last = last - (majorDiseaseEntity.getMax() - majorDiseaseEntity.getMin());
				}

				MajorDiseaseEntity majorDiseaseEntity1 = ma.get(pevNum);
				last = last - (majorDiseaseEntity1.getMax() - costRecord.getPevMa());

				result = result + (majorDiseaseEntity1.getMax() - costRecord.getPevMa()) * majorDiseaseEntity1.getIFactor();
				MajorDiseaseEntity majorDiseaseEntity2 = ma.get(nowNum);
				result = result + (last * majorDiseaseEntity2.getIFactor());
			}
		}
		return result;
	}
	
}
