package com.catmktg.monitoring.StoreMonitor.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class HeartBeatMsg extends GenericJson {
	@Key
	private MessageHeader hdr;

}
