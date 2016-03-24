package com.catmktg.monitoring.StoreMonitor.model;

import java.util.Map;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class StoreHealthData extends GenericJson {

	@Key 
	private Map<String, HeartBeatMsg> healthData;

	public Map<String, HeartBeatMsg> getHealthData() {
		return healthData;
	}

	public void setHealthData(Map<String, HeartBeatMsg> healthData) {
		this.healthData = healthData;
	}

}
