package com.incl.content.schedular.controller;

 

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incl.content.schedular.bean.Log;
import com.incl.content.schedular.repository.LogRepository;
import com.incl.content.schedular.util.CustomException;

 

@RestController
@RequestMapping("imports/v1/watchdog")
public class WatchdogController {

	
	@Autowired
	@Lazy
	LogRepository logRepository;
	
	/**
	 * get watchDogLog
	 * 
	 * @param dropbox          dropboxId of dropbox
	 * @return watchDogLog
	 * @see com.incl.content.schedular.bean.Log
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Log>> getWatchgogByDropbox(@RequestParam(name="dropbox") Integer dropboxID ) {

		
		if(dropboxID != null) {
			PageRequest pageRequest = new PageRequest(0, 30, Sort.Direction.DESC, "id");
			
			System.out.println("Start Getting Watchdog By dropbox:-" + dropboxID);
			return new ResponseEntity<List<Log>>(logRepository.findByDropboxId(dropboxID,pageRequest), HttpStatus.OK);	
		}else {
			throw new CustomException("Job id is required!");
		}
		

	}
	
	/**
	 * get watchDogLog by id
	 * 
	 * @param id          id of watchDogLog
	 * @return watchDogLog
	 * @see com.incl.content.schedular.bean.Log
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Log> getWatchgogLogByID(@PathVariable(value = "id") Integer id) {

		System.out.println("Start Getting Watchdog By id:-" + id);

		return new ResponseEntity<Log>(logRepository.findById(id), HttpStatus.OK);
	}

}
