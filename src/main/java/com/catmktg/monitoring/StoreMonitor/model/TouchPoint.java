package com.catmktg.monitoring.StoreMonitor.model;

import com.catmktg.monitoring.StoreMonitor.model.TouchPoint.Level;

public class TouchPoint {
	private String touchPointId;
	private Level health;
	private String stats;
	
	public enum Level {
	    GOOD,
	    BAD,
	    UGLY
	}
	
	public String getTouchPointId() {
		return touchPointId;
	}

	public void setTouchPointId(String touchPointId) {
		this.touchPointId = touchPointId;
	}

	public TouchPoint(String id) {
		touchPointId = id;
		health = Level.GOOD;
	}

	public String toString() {
		return this.touchPointId;
	}

	public Level health() {
		return health;
	}

	public void setHealth(Level health) {
		this.health = health;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}
}