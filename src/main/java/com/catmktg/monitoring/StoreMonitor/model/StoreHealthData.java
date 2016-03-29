package com.catmktg.monitoring.StoreMonitor.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class StoreHealthData  {

	
	private Map<String, HeartBeatMsg> healthData;

	@JsonAnyGetter
	public Map<String, HeartBeatMsg> any() {
		return healthData;
	}
	
	@JsonAnySetter
	public void set(Map<String, HeartBeatMsg> healthData) {
		this.healthData = healthData;
	}
	
	public StoreHealthData() {
		
	}
}
