package com.framework.entity;

/**
 * 
 * @author Mobingfeng
 * @date 2018年7月12日
 */
public class ScanQRCode {
	
	private Long indexId;
	
	private String openId;
	
	private String employeeNo;
	
	private String QRcode;

	public Long getIndexId() {
		return indexId;
	}

	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getQRcode() {
		return QRcode;
	}

	public void setQRcode(String qRcode) {
		QRcode = qRcode;
	}
}
