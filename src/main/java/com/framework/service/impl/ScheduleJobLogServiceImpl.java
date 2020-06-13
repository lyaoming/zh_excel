package com.framework.service.impl;

import com.framework.dao.ScheduleJobLogDao;
import com.framework.entity.ScheduleJobLogEntity;
import com.framework.service.ScheduleJobLogService;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志实现类
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年12月1日 下午10:34:48
 */
//@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
//	@Autowired
	private ScheduleJobLogDao scheduleJobLogDao;

	/**
	 * 根据ID，查询定时任务日志
	 */
	@Override
	public ScheduleJobLogEntity queryObject(Long jobId) {
		return scheduleJobLogDao.queryObject(jobId);
	}

	/**
	 * 查询定时任务日志列表
	 */
	@Override
	public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
		return scheduleJobLogDao.queryList(map);
	}

	/**
	 * 查询总数
	 */
	@Override
	public int queryTotal(Map<String, Object> map) {
		return scheduleJobLogDao.queryTotal(map);
	}

	/**
	 * 保存定时任务日志
	 */
	@Override
	public void save(ScheduleJobLogEntity log) {
		scheduleJobLogDao.save(log);
	}
}
