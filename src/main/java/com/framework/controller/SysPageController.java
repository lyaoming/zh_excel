package com.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年11月24日 下午11:05:27
 */
@Controller
public class SysPageController {
	@RequestMapping("sys/{url}.html")
	public String page(@PathVariable("url") String url) {
		System.out.println("velocity url: " + url);
		return "sys/" + url + ".html";
	}
	
	/*
	@RequestMapping("{url}.html")
	public String velPage(@PathVariable("url") String url) {
		return url + ".html";
	}
	*/
	
	/*
	@RequestMapping("{url}.jsp")
	public String jspPage(@PathVariable("url") String url) {
		System.out.println("jsp url: " + url);
		return "jsp/" + url + ".jsp";
	}
	*/
	
}
