package com.catmktg.monitoring.StoreMonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Message header
 * @author 
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class MessageHeader {
	@JsonIgnoreProperties
	private String ctry;
	@JsonIgnoreProperties
	private int chn;
	@JsonIgnoreProperties
	private int str;
	@JsonIgnoreProperties
	private long ts;
	
	public MessageHeader() {
		
	}
	
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
