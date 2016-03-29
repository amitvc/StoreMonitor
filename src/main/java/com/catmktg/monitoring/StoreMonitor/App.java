package com.catmktg.monitoring.StoreMonitor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.catmktg.monitoring.StoreMonitor.config.RetailerApiConfig;
import com.catmktg.monitoring.StoreMonitor.model.HeartBeatMsg;
import com.catmktg.monitoring.StoreMonitor.model.StoreHealthData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 * Hello world!
 *
 */
public class App {
	public static final String shoprite_health_api = "http://usa.shoprite.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String riteaid_health_api = "http://usa.shoprite.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String walgreens_health_api = "http://usa.shoprite.personalization.catalinamarketing.com/proxy/rest/pos/health";
	
	
	public static void main(String[] args) throws IOException {
		
		StoreHealthData d = new StoreHealthData();
	    Map<String, HeartBeatMsg> hm = new HashMap<String, HeartBeatMsg>();
	    HeartBeatMsg hb1 = new HeartBeatMsg("USA", 21,396);
	    HeartBeatMsg hb2 = new HeartBeatMsg("USA", 21,345);
	    hm.put("USA-0044-9122", hb1);
	    hm.put("USA-0044-9123", hb2);
	    d.set(hm);
	    String result = new ObjectMapper().writeValueAsString(d);
	    System.out.println(result);
	    
	    Client jerseyClient = ClientBuilder.newClient();
		
		Response response = jerseyClient.target(shoprite_health_api)
        .request(MediaType.APPLICATION_JSON)
        .get();

	    Map<String, HeartBeatMsg> healthData = new HashMap<String, HeartBeatMsg>();
	    healthData = response.readEntity(healthData.getClass());
	    d.set(healthData);
	}
}
