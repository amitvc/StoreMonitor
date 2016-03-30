package com.catmktg.monitoring.StoreMonitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.catmktg.monitoring.StoreMonitor.analyzer.BasicHealthAnalyzer;
import com.catmktg.monitoring.StoreMonitor.config.RetailerApiConfig;

/**
 * Hello world!
 *
 */
public class App {
	public static final String shoprite_health_api = "http://usa.shoprite.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String riteaid_health_api = "http://usa.riteaid.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String walgreens_health_api = "http://usa.walgreens.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String shared_health_api = "http://usa.shared.personalization.catalinamarketing.com/proxy/rest/pos/health";

	public static void main(String[] args) throws IOException {

		RetailerApiConfig shopRiteConfig = new RetailerApiConfig();
		shopRiteConfig.setRetailerName("ShopRite");
		shopRiteConfig.setHealthApi(shoprite_health_api);

		RetailerApiConfig riteAidConfig = new RetailerApiConfig();
		riteAidConfig.setRetailerName("RiteAid");
		riteAidConfig.setHealthApi(riteaid_health_api);

		RetailerApiConfig walgreensConfig = new RetailerApiConfig();
		walgreensConfig.setRetailerName("Walgreens");
		walgreensConfig.setHealthApi(walgreens_health_api);

		RetailerApiConfig sharedConfig = new RetailerApiConfig();
		sharedConfig.setRetailerName("Shared");
		sharedConfig.setHealthApi(shared_health_api);

		List<RetailerApiConfig> configList = Arrays.asList(sharedConfig, riteAidConfig, walgreensConfig);
		
		SchedulerFactory schedFact = new StdSchedulerFactory();

		Scheduler sched;
		try {
			sched = schedFact.getScheduler();
			sched.start();

			// define the job and tie it to our HelloJob class
			JobDetail job = JobBuilder.newJob(BasicHealthAnalyzer.class).withIdentity("healthCheckup", "group1").build();
			job.getJobDataMap().put("retailerConfig", configList);

			// Run the health checkup job every 60 seconds
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("healthCheckup", "group1").startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60).repeatForever())
					.build();
			// Tell quartz to schedule the job using our trigger
			sched.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
