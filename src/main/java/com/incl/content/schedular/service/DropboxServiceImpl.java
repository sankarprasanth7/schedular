/**
 * 
 */
package com.incl.content.schedular.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.incl.content.schedular.bean.Job;
import com.incl.content.schedular.bean.FetchRequest;
import com.incl.content.schedular.job.DropboxPollerJob;
import com.incl.content.schedular.repository.JobRepository;

/**
 * @author prasanth
 *
 */
@Service
public class DropboxServiceImpl implements DropboxService {
	
	@Autowired
	@Lazy
	JobService jobService;
	
	@Autowired
	JobRepository jobRepository;

	@Override
	public Job findById(int id) {
		// TODO Auto-generated method stub
		return jobRepository.findById(id);
	}

	@Override
	public Job findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		return jobRepository.findAll();
	}


	@Override
	public Job updateDropbox(Job db) {
		// TODO Auto-generated method stub
		return jobRepository.save(db);
	}

	@Override
	public Job addDropbox(Job db) {
		return  jobRepository.save(db);
	}

	@Override
	public Job findBySender(int senderId) {
		// TODO Auto-generated method stub
		return jobRepository.findBySender(senderId);
	}

	@Override
	public boolean addCron(Job job) {
		// TODO Auto-generated method stub
		
		boolean status = jobService.scheduleDropboxCronJob(job, DropboxPollerJob.class, new Date(), job.getCron(),null);
		return status;
		
	}
	
	@Override
	public boolean deleteCron(Job job) {
		// TODO Auto-generated method stub
		return jobService.deleteJob(""+job.getId());
	}
	
 

	@Override
	public boolean fetchJobs(Job job, FetchRequest fetchRequest) {
		// TODO Auto-generated method stub
		boolean status = jobService.scheduleDropboxCronJob(job, DropboxPollerJob.class, new Date(), job.getCron(),fetchRequest);
		return status;
	}

	@Override
	public boolean deleteDropbox(Job job) {
		jobRepository.delete(job);
		return false;
	}
	
	@Override
	public List<Job> findAllByGroupId(int id) {
		// TODO Auto-generated method stub
		return jobRepository.findAllByGroup(id);
	}


}
