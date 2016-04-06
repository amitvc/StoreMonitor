package com.catmktg.monitoring.StoreMonitor.config;

public class RetailerApiConfig {
	private String healthApi;
	private String retailerName;
	private int touchPointCount;
	
	public String getHealthApi() {
		return healthApi;
	}
	public void setHealthApi(String healthApi) {
		this.healthApi = healthApi;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public int getTouchPointCount() {
		return touchPointCount;
	}
	public void setTouchPointCount(int touchPointCount) {
		this.touchPointCount = touchPointCount;
	}
}
