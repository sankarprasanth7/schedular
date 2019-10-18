package com.incl.content.schedular.service;

import java.util.List;

import com.incl.content.schedular.bean.Job;
import com.incl.content.schedular.bean.FetchRequest;

/**
 * Created by naresh u.
 */

public interface DropboxService {
	Job findByUsername(String username);

	List<Job> findAll();

	boolean addCron(Job job);

	Job updateDropbox(Job db);

	Job addDropbox(Job db);

	Job findById(int id);

	Job findBySender(int senderId);

	boolean deleteCron(Job job);
	boolean deleteDropbox(Job job);
	boolean fetchJobs(Job job,FetchRequest fetchRequest);
	
	public List<Job> findAllByGroupId(int id);
}