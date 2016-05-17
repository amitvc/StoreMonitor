package com.catmktg.monitoring.StoreMonitor.analyzer;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.quartz.Job;
import com.catmktg.monitoring.StoreMonitor.config.RetailerApiConfig;
import com.catmktg.monitoring.StoreMonitor.model.HeartBeatMsg;
import com.catmktg.monitoring.StoreMonitor.model.StoreHealthData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class StoreDataAnalyzer implements Job {
	final static Logger log = Logger.getLogger(StoreDataAnalyzer.class);
	
	public StoreHealthData analyze(RetailerApiConfig retailerConfig) {
		ObjectMapper mapper = new ObjectMapper();
		
		Client jerseyClient = ClientBuilder.newClient();
		Response response = jerseyClient.target(retailerConfig.getHealthApi())
        .request(MediaType.APPLICATION_JSON)
        .get(Response.class);
		StoreHealthData healthData = new StoreHealthData();
	    Map<String, HeartBeatMsg> healthDataMap = new HashMap<String, HeartBeatMsg>();
	    try {
	    	if(response.getStatus() == 200) {
	    		healthDataMap = mapper.readValue(response.readEntity(String.class).toString(),new TypeReference<Map<String, HeartBeatMsg>>(){});	
	    	} else {
	    		log.error("Error while getting health data for retailer "+retailerConfig.getRetailerName());	
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error while getting health data for retailer "+retailerConfig.getRetailerName());
		}
	    healthData.setHealthData(healthDataMap);
	    return healthData;
	}
}
