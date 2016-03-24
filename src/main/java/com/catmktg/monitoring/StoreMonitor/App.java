package com.catmktg.monitoring.StoreMonitor;

import java.io.IOException;

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


/**
 * Hello world!
 *
 */
public class App {
	public static final String shoprite_health_api = "http://usa.shoprite.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String riteaid_health_api = "http://usa.shoprite.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String walgreens_health_api = "http://usa.shoprite.personalization.catalinamarketing.com/proxy/rest/pos/health";
	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	
	public static void main(String[] args) throws IOException {
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
			public void initialize(HttpRequest request) {
				request.setParser(new JsonObjectParser(JSON_FACTORY));
			}
		});

		HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(shoprite_health_api));
		HttpResponse response = request.execute();
		System.out.println(response.getStatusCode());
		StoreHealthData data = response.parseAs(StoreHealthData.class);
		
		System.out.println(data);
	}
}
