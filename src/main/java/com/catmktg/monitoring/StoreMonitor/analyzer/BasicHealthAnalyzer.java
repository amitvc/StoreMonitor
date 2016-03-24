package com.catmktg.monitoring.StoreMonitor.analyzer;

import java.io.IOException;

import com.catmktg.monitoring.StoreMonitor.config.RetailerApiConfig;
import com.catmktg.monitoring.StoreMonitor.model.StoreHealthData;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

public class BasicHealthAnalyzer implements StoreDataAnalyzer {
	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	public void analyze(RetailerApiConfig retailerConfig) {
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
			public void initialize(HttpRequest request) {
				request.setParser(new JsonObjectParser(JSON_FACTORY));
			}
		});

		HttpRequest request;
		try {
			request = requestFactory.buildGetRequest(new GenericUrl(retailerConfig.getHealthApi()));
			HttpResponse response = request.execute();
			System.out.println(response.getStatusCode());
			StoreHealthData data = response.parseAs(StoreHealthData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
