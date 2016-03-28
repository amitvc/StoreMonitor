package com.catmktg.monitoring.StoreMonitor.model;

/**
 * Message header
 * @author 
 *
 */
public class MessageHeader {
	
	private String ctry;
	private int chn;
	private int str;
	private long ts;
	
	public String getCtry() {
		return ctry;
	}
	public void setCtry(String ctry) {
		this.ctry = ctry;
	}
	public int getChn() {
		return chn;
	}
	public void setChn(int chn) {
		this.chn = chn;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	public long getTs() {
		return ts;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
}
