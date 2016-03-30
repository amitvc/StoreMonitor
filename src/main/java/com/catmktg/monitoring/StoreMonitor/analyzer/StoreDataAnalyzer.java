package com.catmktg.monitoring.StoreMonitor.analyzer;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.quartz.Job;

import com.catmktg.monitoring.StoreMonitor.config.RetailerApiConfig;
import com.catmktg.monitoring.StoreMonitor.model.HeartBeatMsg;
import com.catmktg.monitoring.StoreMonitor.model.StoreHealthData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class StoreDataAnalyzer implements Job {
	
	public StoreHealthData analyze(RetailerApiConfig retailerConfig) {
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
	    return healthData;
	}
}
