package com.incl.content.schedular.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.incl.content.schedular.bean.Job;
import com.incl.content.schedular.bean.FetchRequest;
import com.incl.content.schedular.config.AppConfig;
import com.incl.content.schedular.security.Security;
import com.incl.content.schedular.service.DropboxService;

@RestController
@RequestMapping("imports/v1/dropbox")
public class DropBoxController {

	@Autowired
	@Lazy
	DropboxService dropboxService;

	@Autowired
	AppConfig appConfig;

	/**
	 * get all dropboxes
	 * 
	 * @return list of dropboxes
	 * @see com.incl.content.schedular.bean.Job
	 */
	@RequestMapping()
	public ResponseEntity<List<Job>> getDropBox() {

		System.out.println("Start Getting Job");

		return new ResponseEntity<List<Job>>(dropboxService.findAll(), HttpStatus.OK);

	}

	/**
	 * get all dropbox by id
	 * 
	 * @param id Id value of dropbox
	 * @return dropbox
	 * @see com.incl.content.schedular.bean.Job
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Job> getDropBoxByID(@PathVariable(value = "id") Integer id) {

		System.out.println("Start Getting Job By id:-" + id);

		return new ResponseEntity<Job>(dropboxService.findById(id), HttpStatus.OK);
	}

	/**
	 * get all dropbox by senderId
	 * 
	 * @param id senderId
	 * @return dropbox
	 * @see com.incl.content.schedular.bean.Job
	 */
	@RequestMapping(value = "/sender/{id}", method = RequestMethod.GET)
	public ResponseEntity<Job> getDropboxBySender(@PathVariable(value = "id") Integer senderId) {
		System.out.println("Start Getting Job By sender id:-" + senderId);

		return new ResponseEntity<Job>(dropboxService.findBySender(senderId), HttpStatus.OK);
	}

	/**
	 * create dropbox
	 * 
	 * @body dropbox com.incl.content.schedular.bean.Job
	 * @return dropbox
	 * @see com.incl.content.schedular.bean.Job
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Job> addDropbox(@RequestBody Job job) {
		System.out.println("From Request Body ---->" + job.toString());

		String admin = Security.getName(SecurityContextHolder.getContext().getAuthentication());
		if (admin != null)
			job.setAdmin(admin);
		Job dbDropbox = dropboxService.addDropbox(job);

		if (dbDropbox.getIsActive()) {
			dropboxService.addCron(dbDropbox);
		}

		return new ResponseEntity<Job>(dbDropbox, HttpStatus.CREATED);
	}

	/**
	 * update dropbox
	 * 
	 * @param id dropboxId
	 * @body dropbox com.incl.content.schedular.bean.Job
	 * @return dropbox
	 * @see com.incl.content.schedular.bean.Job
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Job> updateDropbox(@PathVariable("id") Integer id, @RequestBody Job job) {
		Job db = dropboxService.findById(job.getId());
		db.setCron(job.getCron());
		db.setIsActive(job.getIsActive());
		System.out.println("db::::" + db.getGroup_id());
		System.out.println("From Request Body ---->" + job.toString());
		String admin = Security.getName(SecurityContextHolder.getContext().getAuthentication());
		if (admin != null)
			db.setAdmin(admin);
		Job dbDropbox = dropboxService.updateDropbox(db);
		dropboxService.deleteCron(dbDropbox);
		if (dbDropbox.getIsActive()) {
			dropboxService.addCron(dbDropbox);
		}
		return new ResponseEntity<Job>(dbDropbox, HttpStatus.OK);
	}

	/**
	 * rerun ropbox
	 * 
	 * @param id dropboxId
	 * @body dropbox com.incl.content.schedular.bean.Job
	 * @return dropbox
	 * @see com.incl.content.schedular.bean.Job
	 */
	@RequestMapping(value = "/{id}/reset", method = RequestMethod.POST)
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Job> rerunDropbox(@PathVariable("id") Integer id) {

		Job dbDropbox = dropboxService.findById(id);

		dbDropbox.setLastFetch(0);
		dbDropbox.setLastRerun((new Date()).getTime());
		String admin = Security.getName(SecurityContextHolder.getContext().getAuthentication());
		if (admin != null)
			dbDropbox.setAdmin(admin);
		return new ResponseEntity<Job>(dropboxService.updateDropbox(dbDropbox), HttpStatus.OK);

	}

	/**
	 * delete dropbox
	 * 
	 * @param id dropboxId
	 * @body dropbox com.incl.content.schedular.bean.Job
	 * @return status
	 * @see com.incl.content.schedular.bean.Job
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		System.out.println("Delete ---->" + id);
		Job dbDropbox = dropboxService.findById(id);

		String admin = Security.getName(SecurityContextHolder.getContext().getAuthentication());
		if (admin != null) {
			if (dbDropbox.getIsActive()) {
				dropboxService.deleteCron(dbDropbox);
			}
			dropboxService.deleteDropbox(dbDropbox);
		} else {
			return new ResponseEntity<String>("Not admin", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Deleted", HttpStatus.OK);

	}

	/**
	 * fetch ropbox
	 * 
	 * @param id dropboxId
	 * @return dropbox
	 * @see com.incl.content.schedular.bean.Job
	 */
	@RequestMapping(value = "/{id}/fetch", method = RequestMethod.POST)
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Job> fetchDropbox(@PathVariable("id") Integer id,
			@RequestBody FetchRequest fetchRequest) {

		Job dbDropbox = dropboxService.findById(id);
		System.out.println("Job :" + dbDropbox.toString());
		dbDropbox.setLastRerun((new Date()).getTime());
		dbDropbox = dropboxService.updateDropbox(dbDropbox);
		dropboxService.deleteCron(dbDropbox);

		dropboxService.fetchJobs(dbDropbox, fetchRequest);

		return new ResponseEntity<Job>(dbDropbox, HttpStatus.OK);

	}

}
