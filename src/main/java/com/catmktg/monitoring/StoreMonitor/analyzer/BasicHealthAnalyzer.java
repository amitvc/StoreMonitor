package com.catmktg.monitoring.StoreMonitor.analyzer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.catmktg.monitoring.StoreMonitor.config.RetailerApiConfig;
import com.catmktg.monitoring.StoreMonitor.model.HeartBeatMsg;
import com.catmktg.monitoring.StoreMonitor.model.StoreHealthData;
import com.catmktg.monitoring.StoreMonitor.model.TouchPoint;
import com.catmktg.monitoring.StoreMonitor.model.TouchPoint.Level;
import com.catmktg.monitoring.StoreMonitor.ui.ChartPanelUI;

public class BasicHealthAnalyzer extends StoreDataAnalyzer {
	final static Logger log = Logger.getLogger(BasicHealthAnalyzer.class);
	private ChartPanelUI chartPanelUI;
	
	private String statsSummaryForTouchPoints(StoreHealthData healthData) {
		long currentTime = System.currentTimeMillis() / 1000;
		int storesUnderAnHour = 0;
		int storesOverAnHour = 0;
	    for(Map.Entry<String, HeartBeatMsg> entry : healthData.getHealthData().entrySet()) {
	    	long secondsElapsed = currentTime-entry.getValue().getHdr().getTs();
	    	if(secondsElapsed < 0) {
	    		secondsElapsed = 0;
	    	}
	    	if(secondsElapsed > 300 && secondsElapsed < 3600) {
	    		storesUnderAnHour++;
	    	} else if (secondsElapsed > 3600){
	    		storesOverAnHour++;
	    	}
	    }
	    return String.format("%d stores not connected in last 5 mins to an hour, %d stores stores not connected in over an hour", storesUnderAnHour, storesOverAnHour);
	}
	
	private List<TouchPoint> prepareTouchPointList(StoreHealthData healthData) {
		List<TouchPoint> tpList = new ArrayList<TouchPoint>();
		long currentTime = System.currentTimeMillis() / 1000;
		for(Map.Entry<String, HeartBeatMsg> entries : healthData.getHealthData().entrySet()){
			TouchPoint tp = new TouchPoint(entries.getKey());
			long secondsElapsed = currentTime-entries.getValue().getHdr().getTs();
	    	if(secondsElapsed < 0) {
	    		secondsElapsed = 0;
	    	}
	    	if(secondsElapsed > 300 && secondsElapsed < 3600) {
	    		tp.setStats(String.format("%d mins %d seconds", (secondsElapsed/60), ((secondsElapsed%60))));
	    		tp.setHealth(Level.BAD);
	    	} else if (secondsElapsed > 3600){
	    		tp.setStats(String.format("%d hours %d mins %d seconds", secondsElapsed/3600, (secondsElapsed%3600)/60, (secondsElapsed%3600)%60));
	    		tp.setHealth(Level.UGLY);
	    	}
			tpList.add(tp);
		}
		return tpList;
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<RetailerApiConfig> configList = (List<RetailerApiConfig>)context.getJobDetail().getJobDataMap().get("retailerConfig");
		if(chartPanelUI == null) {
			this.chartPanelUI = (ChartPanelUI)	context.getJobDetail().getJobDataMap().get("chartPanelUI");
		}
		
		for(RetailerApiConfig config : configList) {
			StoreHealthData healthData = analyze(config);
			long currentTime = System.currentTimeMillis() / 1000;
			chartPanelUI.addTouchPoints(config.getRetailerName(), prepareTouchPointList(healthData));
			StringBuilder sb = new StringBuilder();
			sb.append("Generating health report for " + config.getRetailerName() + " at current time : " + new Date().toString());
			sb.append(System.lineSeparator());
			sb.append("Store configured : " + config.getTouchPointCount() +  " actual connected : " + healthData.getHealthData().size());
			sb.append(System.lineSeparator());
			sb.append(""+statsSummaryForTouchPoints(healthData));
			log.info(sb.toString());
		    for(Map.Entry<String, HeartBeatMsg> entry : healthData.getHealthData().entrySet()) {
		    	long secondsElapsed = currentTime-entry.getValue().getHdr().getTs();
		    	if(secondsElapsed < 0) {
		    		secondsElapsed = 0;
		    	}
		    	if(secondsElapsed > 300 && secondsElapsed < 3600) {
		    		log.info("TouchPoint : "+ entry.getKey() + " last HB seen "+  String.format("%d mins %d seconds", (secondsElapsed/60), ((secondsElapsed%60))));	
		    	} else if (secondsElapsed > 3600){
		    		log.info("TouchPoint : "+ entry.getKey() + " last HB seen "+   String.format("%d hours %d mins %d seconds", secondsElapsed/3600, (secondsElapsed%3600)/60, (secondsElapsed%3600)%60));
		    	}
		    }	
		}
	}
}
