package com.catmktg.monitoring.StoreMonitor.model;

import java.util.ArrayList;
import java.util.List;
import com.catmktg.monitoring.StoreMonitor.config.RetailerApiConfig;

public class RetailerStat {
	private final RetailerApiConfig apiConfig;
	private List<TouchPointStats> touchPoints;
	private int touchPointsDroppedCount;
	
	
	public RetailerStat(RetailerApiConfig config) {
		this.apiConfig = config;
		touchPoints = new ArrayList<TouchPointStats>();
	}
	
	public List<TouchPointStats> getTouchPoints() {
		return touchPoints;
	}
	
	public void setTouchPoints(List<TouchPointStats> touchPoints) {
		this.touchPoints = touchPoints;
	}
	
	public void addTouchPoint(TouchPointStats tp) {
		this.touchPoints.add(tp);
	}

	public int getTouchPointsDroppedCount() {
		return touchPointsDroppedCount;
	}

	public void setTouchPointsDroppedCount(int touchPointsDroppedCount) {
		this.touchPointsDroppedCount = touchPointsDroppedCount;
	}
}