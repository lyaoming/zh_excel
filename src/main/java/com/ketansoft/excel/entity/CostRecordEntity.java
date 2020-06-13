package com.ketansoft.excel.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 17:15:16
 */
public class CostRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//记录ID
	private Integer cId;
	//人员ID
	private Integer pId;

	private String pName;

	private String inDate;
	//发票或出院日期
	private String outDate;
	//类型
	private Integer classion;
	//个人缴付
	private Double allSelfcost;
	//自付
	private Double selfCost;
	//补充医疗基数
	private Double baseNum;
	//补充医疗系数
	private Double mFactor;
	//补充医疗报销额
	private Double mCost;
	//补充医疗不报销额
	private Double mNocost;
	//重疾自付基数
	private Double maSelf;
	//重疾报销额
	private Double maReduce;
	//合计报销
	private Double costAll;
	//实际自付
	private Double acCost;
	//上次重疾累计
	private Double pevMa;
	//本次重疾累计
	private Double nowMa;
	//省外重疾计算
	private Double outMacount;
	//
	private Integer year;
	//
	private String dName;

	//
	private String sName;

	private String pNumber;

	private String pDepartment;

	private String remark;


	/**
	 * 设置：记录ID
	 */
	public void setCId(Integer cId) {
		this.cId = cId;
	}
	/**
	 * 获取：记录ID
	 */
	public Integer getCId() {
		return cId;
	}
	/**
	 * 设置：人员ID
	 */
	public void setPId(Integer pId) {
		this.pId = pId;
	}
	/**
	 * 获取：人员ID
	 */
	public Integer getPId() {
		return pId;
	}


	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}
	/**
	 * 设置：入院日期
	 */
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	/**
	 * 获取：入院日期
	 */
	public String getInDate() {
		return inDate;
	}
	/**
	 * 设置：发票或出院日期
	 */
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	/**
	 * 获取：发票或出院日期
	 */
	public String getOutDate() {
		return outDate;
	}
	/**
	 * 设置：类型
	 */
	public void setClassion(Integer classion) {
		this.classion = classion;
	}
	/**
	 * 获取：类型
	 */
	public Integer getClassion() {
		return classion;
	}
	/**
	 * 设置：个人缴付
	 */
	public void setAllSelfcost(Double allSelfcost) {
		this.allSelfcost = allSelfcost;
	}
	/**
	 * 获取：个人缴付
	 */
	public Double getAllSelfcost() {
		return allSelfcost;
	}
	/**
	 * 设置：自付
	 */
	public void setSelfCost(Double selfCost) {
		this.selfCost = selfCost;
	}
	/**
	 * 获取：自付
	 */
	public Double getSelfCost() {
		return selfCost;
	}
	/**
	 * 设置：补充医疗基数
	 */
	public void setBaseNum(Double baseNum) {
		this.baseNum = baseNum;
	}
	/**
	 * 获取：补充医疗基数
	 */
	public Double getBaseNum() {
		return baseNum;
	}
	/**
	 * 设置：补充医疗系数
	 */
	public void setMFactor(Double mFactor) {
		this.mFactor = mFactor;
	}
	/**
	 * 获取：补充医疗系数
	 */
	public Double getMFactor() {
		return mFactor;
	}
	/**
	 * 设置：补充医疗报销额
	 */
	public void setMCost(Double mCost) {
		this.mCost = mCost;
	}
	/**
	 * 获取：补充医疗报销额
	 */
	public Double getMCost() {
		return mCost;
	}
	/**
	 * 设置：补充医疗不报销额
	 */
	public void setMNocost(Double mNocost) {
		this.mNocost = mNocost;
	}
	/**
	 * 获取：补充医疗不报销额
	 */
	public Double getMNocost() {
		return mNocost;
	}
	/**
	 * 设置：重疾自付基数
	 */
	public void setMaSelf(Double maSelf) {
		this.maSelf = maSelf;
	}
	/**
	 * 获取：重疾自付基数
	 */
	public Double getMaSelf() {
		return maSelf;
	}
	/**
	 * 设置：重疾报销额
	 */
	public void setMaReduce(Double maReduce) {
		this.maReduce = maReduce;
	}
	/**
	 * 获取：重疾报销额
	 */
	public Double getMaReduce() {
		return maReduce;
	}
	/**
	 * 设置：合计报销
	 */
	public void setCostAll(Double costAll) {
		this.costAll = costAll;
	}
	/**
	 * 获取：合计报销
	 */
	public Double getCostAll() {
		return costAll;
	}
	/**
	 * 设置：实际自付
	 */
	public void setAcCost(Double acCost) {
		this.acCost = acCost;
	}
	/**
	 * 获取：实际自付
	 */
	public Double getAcCost() {
		return acCost;
	}
	/**
	 * 设置：上次重疾累计
	 */
	public void setPevMa(Double pevMa) {
		this.pevMa = pevMa;
	}
	/**
	 * 获取：上次重疾累计
	 */
	public Double getPevMa() {
		return pevMa;
	}
	/**
	 * 设置：本次重疾累计
	 */
	public void setNowMa(Double nowMa) {
		this.nowMa = nowMa;
	}
	/**
	 * 获取：本次重疾累计
	 */
	public Double getNowMa() {
		return nowMa;
	}
	/**
	 * 设置：省外重疾计算
	 */
	public void setOutMacount(Double outMacount) {
		this.outMacount = outMacount;
	}
	/**
	 * 获取：省外重疾计算
	 */
	public Double getOutMacount() {
		return outMacount;
	}

	/**
	 * 设置：
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	/**
	 * 获取：
	 */
	public Integer getYear() {
		return year;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}


	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getpNumber() {
		return pNumber;
	}

	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}

	public String getpDepartment() {
		return pDepartment;
	}

	public void setpDepartment(String pDepartment) {
		this.pDepartment = pDepartment;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
