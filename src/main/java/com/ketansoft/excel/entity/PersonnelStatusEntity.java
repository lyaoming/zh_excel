package com.ketansoft.excel.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:26:07
 */
public class PersonnelStatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer sId;
	//人员状态
	private String sName;

	/**
	 * 设置：
	 */
	public void setSId(Integer sId) {
		this.sId = sId;
	}
	/**
	 * 获取：
	 */
	public Integer getSId() {
		return sId;
	}
	/**
	 * 设置：人员状态
	 */
	public void setSName(String sName) {
		this.sName = sName;
	}
	/**
	 * 获取：人员状态
	 */
	public String getSName() {
		return sName;
	}
}
