package com.catmktg.monitoring.StoreMonitor.model;

public class TouchPoint {
	public String touchPointId;

	public TouchPoint(String id) {
		touchPointId = id;
	}

	public String toString() {
		return this.touchPointId;
	}
}