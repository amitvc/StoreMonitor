package com.catmktg.monitoring.StoreMonitor.model;

public class TouchPointStats {
	private final String touchPointId;
	private long lastSeen;
	private int missedHearthBeats;
	private final String firstSeen;
	
	public TouchPointStats(String id, String firstSeen) {
		this.touchPointId = id;
		this.firstSeen = firstSeen;
	}
	
	public String getTouchPointId() {
		return touchPointId;
	}

	public long getLastSeen() {
		return lastSeen;
	}
	
	public void setLastSeen(long lastSeen) {
		this.lastSeen = lastSeen;
	}
	
	public int getMissedHearthBeats() {
		return missedHearthBeats;
	}

	public void setMissedHearthBeats(int missedHearthBeats) {
		this.missedHearthBeats = missedHearthBeats;
	}

	public String getFirstSeen() {
		return firstSeen;
	}
	
	public String getLastStringReadable() {
		return null;
	}
}
