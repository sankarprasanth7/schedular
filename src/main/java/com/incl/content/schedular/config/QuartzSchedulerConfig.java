package com.incl.content.schedular.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.incl.content.schedular.service.JobsListener;
import com.incl.content.schedular.service.TriggerListner;

@Configuration
public class QuartzSchedulerConfig {

	@Autowired
	DataSource dataSource;

	@Autowired
	private TriggerListner triggerListner;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private JobsListener jobsListener;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public org.quartz.spi.JobFactory jobFactory() {

		AutowiringSpringBeanJobFactory sampleJobFactory = new AutowiringSpringBeanJobFactory();
		sampleJobFactory.setApplicationContext(applicationContext);
		return sampleJobFactory;
	}

	/**
	 * create scheduler
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {

		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setOverwriteExistingJobs(true);
		factory.setDataSource(dataSource);
		Properties quartzProperties = quartzProperties();

		// setting up quartz table prefix by group
		quartzProperties.setProperty("org.quartz.jobStore.tablePrefix", "qrtz_");
		factory.setQuartzProperties(quartzProperties);

		// Register listeners to get notification on Trigger misfire etc
		factory.setGlobalTriggerListeners(triggerListner);
		factory.setGlobalJobListeners(jobsListener);

		factory.setJobFactory(jobFactory());

		return factory;
	}

	/**
	 * Configure quartz using properties file
	 */
	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

}