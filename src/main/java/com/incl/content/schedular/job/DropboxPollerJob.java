package com.incl.content.schedular.job;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.incl.content.schedular.bean.Job;
import com.incl.content.schedular.bean.FetchRequest;
import com.incl.content.schedular.config.AppConfig;
import com.incl.content.schedular.repository.JobRepository;
import com.incl.content.schedular.service.JobService;
import com.incl.content.schedular.service.JobUtil;
import com.incl.content.schedular.util.FetchType;


public class DropboxPollerJob extends QuartzJobBean implements InterruptableJob{
	
	private volatile boolean toStopFlag = true;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	AppConfig appConfig;
	
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		JobKey key = jobExecutionContext.getJobDetail().getKey();
		System.out.println("Cron Job started with key :" + key.getName() + ", Group :"+key.getGroup() + " , Thread Name :"+Thread.currentThread().getName() + " ,Time now :"+new Date());
		
		System.out.println("======================================");
		System.out.println("Accessing annotation example: "+jobService.getAllJobs());
		List<Map<String, Object>> list = jobService.getAllJobs();
		System.out.println("Job list :"+list);
		System.out.println("======================================");
		
		//*********** For retrieving stored key-value pairs ***********/
		JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
		int dropboxId = Integer.parseInt(dataMap.getString(JobUtil.KEY_DROPBOX));
		int type =Integer.parseInt(dataMap.getString(JobUtil.KEY_FETCH_TYPE));
		Job job = jobRepository.findById(dropboxId);
		System.out.println("Job Cron:" + job.getId());
		FetchRequest fetchRequest= new FetchRequest();
		if(type == FetchType.FETCH_MANUAL.ordinal()) {
			fetchRequest.setType(FetchType.FETCH_MANUAL.ordinal());
			fetchRequest.setFrom(Long.parseLong(dataMap.getString(JobUtil.KEY_FETCH_FROM)));
			fetchRequest.setTo(Long.parseLong(dataMap.getString(JobUtil.KEY_FETCH_TO)));
		}else {
			fetchRequest.setType(FetchType.FETCH_POLL.ordinal());
		}
		
		if(!jobService.isJobRunning(jobExecutionContext.getJobDetail().getKey().getName()))
//		dropboxFetcher.fetchDropbox(dropbox,fetchRequest);
		
		System.out.println("Thread: "+ Thread.currentThread().getName() +" stopped.");
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		System.out.println("Stopping thread... ");
		toStopFlag = false;
	}

}