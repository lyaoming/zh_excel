package com.ketansoft.excel.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:47:28
 */
public class MajorDiseaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer iId;
	//名称
	private String iName;
	//最小值
	private Double min;
	//最大值
	private Double max;
	//系数
	private Double iFactor;

	/**
	 * 设置：
	 */
	public void setIId(Integer iId) {
		this.iId = iId;
	}
	/**
	 * 获取：
	 */
	public Integer getIId() {
		return iId;
	}
	/**
	 * 设置：名称
	 */
	public void setIName(String iName) {
		this.iName = iName;
	}
	/**
	 * 获取：名称
	 */
	public String getIName() {
		return iName;
	}
	/**
	 * 设置：最小值
	 */
	public void setMin(Double min) {
		this.min = min;
	}
	/**
	 * 获取：最小值
	 */
	public Double getMin() {
		return min;
	}
	/**
	 * 设置：最大值
	 */
	public void setMax(Double max) {
		this.max = max;
	}
	/**
	 * 获取：最大值
	 */
	public Double getMax() {
		return max;
	}
	/**
	 * 设置：系数
	 */
	public void setIFactor(Double iFactor) {
		this.iFactor = iFactor;
	}
	/**
	 * 获取：系数
	 */
	public Double getIFactor() {
		return iFactor;
	}
}
