package com.framework.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * 返回数据
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年10月27日 下午9:59:27
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 0);
	}
	
	public R(int code, String msg) {
		put("code", code);
		put("msg", msg);
	}
	
	public R(int code, String msg, net.sf.json.JSONObject data) {
		put("code", code);
		put("msg", msg);
		put("data", data);
	}
	
	public static R result(int code, String msg, net.sf.json.JSONObject data) {
		return new R(code, msg, data);
	}
	
	public static R result2(int code, String msg) {
		return new R(code, msg);
	}
	public static R result3(int code, String msg, String data) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.put("data",data);
		return r;
	}
	
	public static R result4(int code, String msg, com.alibaba.fastjson.JSONObject data) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.put("data",data);
		return r;
	}

	public static R error() {
		return error(500, "未知异常，请联系管理员吧");
	}

	public static R error(String msg) {
		return error(500, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
