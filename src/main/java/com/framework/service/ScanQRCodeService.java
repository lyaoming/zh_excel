package com.framework.service;

import com.framework.entity.ScanQRCode;

/**
 * 
 * @author Mobingfeng
 * @date 2018年7月12日
 */
public interface ScanQRCodeService {
	
	ScanQRCode getOpenIdByQRcode(String QRcode);
	
	boolean insert(ScanQRCode scan);
	
}
