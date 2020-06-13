package com.framework.utils;

import java.text.DecimalFormat;

/**
 * 数字格式处理
 * @author Mobingfeng
 * @email 1025569592@qq.com
 * @date 2019年05月15日 下午12:53:33
 */
public class NumberUtils {
	/** 数字格式(0.00) */
	public final static String Number_PATTERN = "0.00";

	public static String format(Double number) {
		return format(number, Number_PATTERN);
	}

	public static String format(Double number, String pattern) {
		if (number != null) {
			DecimalFormat df = new DecimalFormat(pattern);
			return df.format(number);
		}
		return null;
	}
}
