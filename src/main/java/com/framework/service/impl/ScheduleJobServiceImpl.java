package com.framework.service.impl;

import com.framework.dao.ScheduleJobDao;
import com.framework.entity.ScheduleJobEntity;
import com.framework.service.ScheduleJobService;
import com.framework.utils.Constant.ScheduleStatus;
import com.framework.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务实现类
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年11月28日 上午9:55:32
 */
//@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
//	@Autowired
	private Scheduler scheduler;
//	@Autowired
	private ScheduleJobDao schedulerJobDao;

	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init() {
		List<ScheduleJobEntity> scheduleJobList = schedulerJobDao.queryList(new HashMap<String, Object>());
		for (ScheduleJobEntity scheduleJob : scheduleJobList) {
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
			// 如果不存在，则创建
			if (cronTrigger == null) {
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
			} else {
				ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
			}
		}
	}

	/**
	 * 根据ID，查询定时任务
	 */
	@Override
	public ScheduleJobEntity queryObject(Long jobId) {
		return schedulerJobDao.queryObject(jobId);
	}

	/**
	 * 查询定时任务列表
	 */
	@Override
	public List<ScheduleJobEntity> queryList(Map<String, Object> map) {
		return schedulerJobDao.queryList(map);
	}

	/**
	 * 查询总数
	 */
	@Override
	public int queryTotal(Map<String, Object> map) {
		return schedulerJobDao.queryTotal(map);
	}

	/**
	 * 保存定时任务
	 */
	@Override
	@Transactional
	public void save(ScheduleJobEntity scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
		schedulerJobDao.save(scheduleJob);

		ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
	}

	/**
	 * 更新定时任务
	 */
	@Override
	@Transactional
	public void update(ScheduleJobEntity scheduleJob) {
		ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);

		schedulerJobDao.update(scheduleJob);
	}

	/**
	 * 批量删除定时任务
	 */
	@Override
	@Transactional
	public void deleteBatch(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.deleteScheduleJob(scheduler, jobId);
		}

		// 删除数据
		schedulerJobDao.deleteBatch(jobIds);
	}

	/**
	 * 批量更新定时任务状态
	 */
	@Override
	public int updateBatch(Long[] jobIds, int status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", jobIds);
		map.put("status", status);
		return schedulerJobDao.updateBatch(map);
	}

	/**
	 * 立即执行
	 */
	@Override
	@Transactional
	public void run(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.run(scheduler, queryObject(jobId));
		}
	}

	/**
	 * 暂停运行
	 */
	@Override
	@Transactional
	public void pause(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.pauseJob(scheduler, jobId);
		}

		updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
	}

	/**
	 * 恢复运行
	 */
	@Override
	@Transactional
	public void resume(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.resumeJob(scheduler, jobId);
		}

		updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
	}
}
