package com.catmktg.monitoring.StoreMonitor.model;

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
	
	private MessageHeader hdr;
	
	private long ts;
	
	private String seq;
	
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
