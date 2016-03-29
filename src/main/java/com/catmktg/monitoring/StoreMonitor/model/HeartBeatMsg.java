package com.catmktg.monitoring.StoreMonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HeartBeatMsg  {
	
	public HeartBeatMsg(String cc, int store, int chain) {
		hdr = new MessageHeader();
		hdr.setCtry(cc);
		hdr.setStr(store);
		hdr.setChn(chain);
		ts = System.currentTimeMillis();
		seq = "0";
		_t = "ss";
	}
	
	public HeartBeatMsg() {
		
	}
	
	@JsonIgnoreProperties
	@JsonProperty("hdr")
	private MessageHeader hdr;
	
	@JsonIgnoreProperties
	@JsonProperty("ts")
	private long ts;
	
	@JsonIgnoreProperties
	@JsonProperty("seq")
	private String seq;
	
	@JsonProperty("_t")
	@JsonIgnoreProperties
	private String _t;

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String get_t() {
		return _t;
	}

	public void set_t(String _t) {
		this._t = _t;
	}
	
	public MessageHeader getHdr() {
		return hdr;
	}

	public void setHdr(MessageHeader hdr) {
		this.hdr = hdr;
	}
}
