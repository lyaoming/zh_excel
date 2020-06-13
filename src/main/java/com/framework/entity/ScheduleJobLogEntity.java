package com.framework.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时执行日志
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年12月1日 下午10:26:18
 */
public class ScheduleJobLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志id
	 */
	private Long logId;

	/**
	 * 任务id
	 */
	private Long jobId;

	/**
	 * spring bean名称
	 */
	private String beanName;

	/**
	 * 方法名
	 */
	private String methodName;

	/**
	 * 参数
	 */
	private String params;

	/**
	 * 任务状态 0：成功 1：失败
	 */
	private Integer status;

	/**
	 * 失败信息
	 */
	private String error;

	/**
	 * 耗时(单位：毫秒)
	 */
	private Integer times;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 获取：日志id
	 * @return Long
	 */
	public Long getLogId() {
		return logId;
	}

	/**
	 * 设置：日志id
	 * @param logId 日志id
	 */
	public void setLogId(Long logId) {
		this.logId = logId;
	}

	/**
	 * 获取：任务id
	 * @return Long
	 */
	public Long getJobId() {
		return jobId;
	}

	/**
	 * 设置：任务id
	 * @param jobId 任务id
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	/**
	 * 获取：spring bean名称
	 * @return String
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * 设置：spring bean名称
	 * @param beanName spring bean名称
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	/**
	 * 获取：方法名
	 * @return String
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * 设置：方法名
	 * @param methodName 方法名
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * 获取：参数
	 * @return String
	 */
	public String getParams() {
		return params;
	}

	/**
	 * 设置：参数
	 * @param params 参数
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * 获取：任务状态 0：成功 1：失败
	 * @return Integer
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置：任务状态 0：成功 1：失败
	 * @param status 任务状态 0：成功 1：失败
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：失败信息
	 * @return String
	 */
	public String getError() {
		return error;
	}

	/**
	 * 设置：失败信息
	 * @param error 失败信息
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * 获取：耗时(单位：毫秒)
	 * @return Integer
	 */
	public Integer getTimes() {
		return times;
	}

	/**
	 * 设置：耗时(单位：毫秒)
	 * @param times 耗时(单位：毫秒)
	 */
	public void setTimes(Integer times) {
		this.times = times;
	}

	/**
	 * 获取：创建时间
	 * @return Date
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
