package com.ketansoft.excel.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 15:03:11
 */
public class PersonnelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer pId;
	//姓名
	private String pName;
	//部门
	private String pDepartment;
	//状态
	private Integer pStatus;


	private String pNumber;

	private String sName;



	/**
	 * 设置：
	 */
	public void setPId(Integer pId) {
		this.pId = pId;
	}
	/**
	 * 获取：
	 */
	public Integer getPId() {
		return pId;
	}
	/**
	 * 设置：姓名
	 */
	public void setPName(String pName) {
		this.pName = pName;
	}
	/**
	 * 获取：姓名
	 */
	public String getPName() {
		return pName;
	}
	/**
	 * 设置：部门
	 */
	public void setPDepartment(String pDepartment) {
		this.pDepartment = pDepartment;
	}
	/**
	 * 获取：部门
	 */
	public String getPDepartment() {
		return pDepartment;
	}
	/**
	 * 设置：状态
	 */
	public void setPStatus(Integer pStatus) {
		this.pStatus = pStatus;
	}
	/**
	 * 获取：状态
	 */
	public Integer getPStatus() {
		return pStatus;
	}


	public String getpNumber() {
		return pNumber;
	}

	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}
}
