package com.catmktg.monitoring.StoreMonitor.analyzer;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.catmktg.monitoring.StoreMonitor.config.RetailerApiConfig;
import com.catmktg.monitoring.StoreMonitor.model.HeartBeatMsg;
import com.catmktg.monitoring.StoreMonitor.model.StoreHealthData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicHealthAnalyzer implements StoreDataAnalyzer {
	
	public void analyze(RetailerApiConfig retailerConfig) {
		ObjectMapper mapper = new ObjectMapper();
		
		Client jerseyClient = ClientBuilder.newClient();
		Response response = jerseyClient.target(retailerConfig.getHealthApi())
        .request(MediaType.APPLICATION_JSON)
        .get(Response.class);
		StoreHealthData healthData = new StoreHealthData();
	    Map<String, HeartBeatMsg> healthDataMap = new HashMap<String, HeartBeatMsg>();
	    try {
	    	healthDataMap = mapper.readValue(response.readEntity(String.class).toString(),new TypeReference<Map<String, HeartBeatMsg>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    healthData.setHealthData(healthDataMap);
	    long currentTime = System.currentTimeMillis() / 1000;
	    System.out.println("Generating health report for " + retailerConfig.getRetailerName() + " current time : " + currentTime);
	    for(Map.Entry<String, HeartBeatMsg> entry : healthDataMap.entrySet()) {
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
