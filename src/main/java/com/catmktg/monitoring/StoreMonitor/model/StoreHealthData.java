package com.catmktg.monitoring.StoreMonitor.model;

import java.util.Map;

public class StoreHealthData  {

	private Map<String, HeartBeatMsg> healthData;

	public Map<String, HeartBeatMsg> getHealthData() {
		return healthData;
	}

	public void setHealthData(Map<String, HeartBeatMsg> healthData) {
		this.healthData = healthData;
	}
}
