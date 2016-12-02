package com.catmktg.monitoring.StoreMonitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import org.joda.time.DateTime;
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
import com.catmktg.monitoring.StoreMonitor.model.RetailerStat;
import com.catmktg.monitoring.StoreMonitor.ui.ChartPanelUI;


/**
 * Hello world!
 *
 */
public class App {
	public static final String shoprite_health_api = "http://usa.shoprite.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String riteaid_health_api = "http://usa.riteaid.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String walgreens_health_api = "http://usa.walgreens.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String shared_health_api = "http://usa.shared.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String foodlion_health_api = "http://usa.foodlion.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String tops_health_api = "http://usa.tops.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String target_health_api = "http://usa.target.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String kroger_health_api = "http://usa.kroger.personalization.catalinamarketing.com/proxy/rest/pos/health";

	private ChartPanelUI chartPanel;
	
	public void createUI() {
		JFrame mainFrame = new JFrame("Heart Beat Monitor");
		chartPanel = new ChartPanelUI();
		mainFrame.add(chartPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window.
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	private void scheduleJob(List<RetailerApiConfig> configList) {
		SchedulerFactory schedFact = new StdSchedulerFactory();

		Scheduler sched;
		try {
			sched = schedFact.getScheduler();
			sched.start();

			// define the job and tie it to our HelloJob class
			JobDetail job = JobBuilder.newJob(BasicHealthAnalyzer.class).withIdentity("healthCheckup", "group1").build();
			job.getJobDataMap().put("retailerConfig", configList);
			job.getJobDataMap().put("chartPanelUI", chartPanel);
			
			for(RetailerApiConfig config : configList) {
				job.getJobDataMap().put(config.getRetailerName(), new RetailerStat(config));
			}
			// Run the health checkup job every 60 seconds
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("healthCheckup", "group1").startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(120).repeatForever())
					.build();
			// Tell quartz to schedule the job using our trigger
			sched.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		long t = System.currentTimeMillis();
		boolean runWithUI = false;
		final DateTime dt = new DateTime(t);
		System.out.println(dt.toDateTime().toString());
		
		if(args.length > 0 && args[0].equalsIgnoreCase("ui")) {
			runWithUI = true;
		}
		
		RetailerApiConfig shopRiteConfig = new RetailerApiConfig();
		shopRiteConfig.setRetailerName("ShopRite");
		shopRiteConfig.setHealthApi(shoprite_health_api);
		shopRiteConfig.setTouchPointCount(270);

		RetailerApiConfig riteAidConfig = new RetailerApiConfig();
		riteAidConfig.setRetailerName("RiteAid");
		riteAidConfig.setHealthApi(riteaid_health_api);
		riteAidConfig.setTouchPointCount(4550);

		RetailerApiConfig walgreensConfig = new RetailerApiConfig();
		walgreensConfig.setRetailerName("Walgreens");
		walgreensConfig.setHealthApi(walgreens_health_api);
		walgreensConfig.setTouchPointCount(7716);

		RetailerApiConfig sharedConfig = new RetailerApiConfig();
		sharedConfig.setRetailerName("Shared");
		sharedConfig.setHealthApi(shared_health_api);
		sharedConfig.setTouchPointCount(3045);
		
		RetailerApiConfig foodlionConfig = new RetailerApiConfig();
		foodlionConfig.setRetailerName("FoodLion");
		foodlionConfig.setHealthApi(foodlion_health_api);
		foodlionConfig.setTouchPointCount(1104);
		
		RetailerApiConfig topsRiteConfig = new RetailerApiConfig();
		topsRiteConfig.setRetailerName("Tops");
		topsRiteConfig.setHealthApi(tops_health_api);
		topsRiteConfig.setTouchPointCount(169);
		
		RetailerApiConfig targetConfig = new RetailerApiConfig();
		targetConfig.setRetailerName("Target");
		targetConfig.setHealthApi(target_health_api);
		targetConfig.setTouchPointCount(1794);

		RetailerApiConfig krogerConfig = new RetailerApiConfig();
		krogerConfig.setRetailerName("Kroger");
		krogerConfig.setHealthApi(kroger_health_api);
		krogerConfig.setTouchPointCount(2200);


		List<RetailerApiConfig> configList = Arrays.asList(sharedConfig, riteAidConfig, walgreensConfig,
				foodlionConfig,shopRiteConfig,topsRiteConfig,targetConfig,krogerConfig);
		App app = new App();
		if(runWithUI) {
			app.createUI();	
		} 
		app.scheduleJob(configList);	
	}
}
