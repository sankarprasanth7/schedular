package com.incl.content.schedular.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.quartz.QuartzJobBean;

import com.incl.content.schedular.bean.Job;
import com.incl.content.schedular.bean.FetchRequest;

public interface JobService {
	boolean scheduleOneTimeJob(String jobName, Class<? extends QuartzJobBean> jobClass, Date date);
	boolean scheduleCronJob(String jobName, Class<? extends QuartzJobBean> jobClass, Date date, String cronExpression);
	
	boolean updateOneTimeJob(String jobName, Date date);
	boolean updateCronJob(String jobName, Date date, String cronExpression);
	
	boolean unScheduleJob(String jobName);
	boolean deleteJob(String jobName);
	boolean pauseJob(String jobName);
	boolean resumeJob(String jobName);
	boolean startJobNow(String jobName);
	boolean isJobRunning(String jobName);
	List<Map<String, Object>> getAllJobs();
	boolean isJobWithNamePresent(String jobName);
	String getJobState(String jobName);
	boolean stopJob(String jobName);
	boolean scheduleDropboxCronJob(Job job, Class<? extends QuartzJobBean> jobClass, Date date,
			String cronExpression,FetchRequest fetchRequest);
 
}
