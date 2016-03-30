package com.catmktg.monitoring.StoreMonitor.analyzer;

import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.catmktg.monitoring.StoreMonitor.config.RetailerApiConfig;
import com.catmktg.monitoring.StoreMonitor.model.HeartBeatMsg;
import com.catmktg.monitoring.StoreMonitor.model.StoreHealthData;

public class BasicHealthAnalyzer extends StoreDataAnalyzer {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<RetailerApiConfig> configList = (List<RetailerApiConfig>)context.getJobDetail().getJobDataMap().get("retailerConfig");
		for(RetailerApiConfig config : configList) {
			StoreHealthData healthData = analyze(config);
			long currentTime = System.currentTimeMillis() / 1000;
		    System.out.println("Generating health report for " + config.getRetailerName() + " current time : " + currentTime);
		    for(Map.Entry<String, HeartBeatMsg> entry : healthData.getHealthData().entrySet()) {
		    	long secondsElapsed = currentTime-entry.getValue().getHdr().getTs();
		    	if(secondsElapsed < 0) {
		    		secondsElapsed = 0;
		    	}
		    	if(secondsElapsed > 60 && secondsElapsed < 3600) {
		    		System.out.println("TouchPoint : "+ entry.getKey() + " last HB seen "+   (secondsElapsed/60) +  " mins");	
		    	} else if (secondsElapsed > 3600){
		    		System.out.println("TouchPoint : "+ entry.getKey() + " last HB seen "+   (secondsElapsed/3600) +  " hours");
		    	}
		    }	
		}		
	}
}
