package com.incl.content.schedular.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.incl.content.schedular.bean.Job;
import com.incl.content.schedular.service.DropboxService;
import com.incl.content.schedular.service.DropboxServiceImpl;

/**
 * @author sobha
 *
 */
@Configuration
public class AppConfig {

	@Value("${API}")
	private String api;

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	@Autowired
	DropboxServiceImpl dropboxService;

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(1);
		threadPoolTaskScheduler.setThreadNamePrefix("BackupJobsThreadPool");
		return threadPoolTaskScheduler;
	}

	@Bean
	public boolean initCron() {
		// Init Cron Process by drop box group
		System.out.println("Init Cron Process for dropboxes");

		List<Job> dropboxes = dropboxService.findAll();
		if (dropboxes != null) {
			for (Job db : dropboxes) {
				System.out.println(db.getPublishername());
				db.setIsRunning(false);
				dropboxService.updateDropbox(db);
				if (db.getIsActive()) {
					// Initiate a cron for each drop box with the config
					dropboxService.addCron(db);
				}

			}

		}
		return true;
	}
}
