package com.ketansoft.excel.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 16:14:20
 */
public class MedicalFactorEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer dId;
	//名称
	private String dName;
	//系数
	private Double dFactor;

	/**
	 * 设置：
	 */
	public void setDId(Integer dId) {
		this.dId = dId;
	}
	/**
	 * 获取：
	 */
	public Integer getDId() {
		return dId;
	}
	/**
	 * 设置：名称
	 */
	public void setDName(String dName) {
		this.dName = dName;
	}
	/**
	 * 获取：名称
	 */
	public String getDName() {
		return dName;
	}
	/**
	 * 设置：系数
	 */
	public void setDFactor(Double dFactor) {
		this.dFactor = dFactor;
	}
	/**
	 * 获取：系数
	 */
	public Double getDFactor() {
		return dFactor;
	}
}
