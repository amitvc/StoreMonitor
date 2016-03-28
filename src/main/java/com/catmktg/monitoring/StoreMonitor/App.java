package com.catmktg.monitoring.StoreMonitor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.catmktg.monitoring.StoreMonitor.model.HeartBeatMsg;
import com.catmktg.monitoring.StoreMonitor.model.StoreHealthData;
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
		
		Gson gson = new GsonBuilder().create();
		StoreHealthData d = new StoreHealthData();
	    Map<String, HeartBeatMsg> hm = new HashMap<String, HeartBeatMsg>();
	    HeartBeatMsg hb1 = new HeartBeatMsg("USA", 21,396);
	    HeartBeatMsg hb2 = new HeartBeatMsg("USA", 21,345);
	    hm.put("USA-0044-9122", hb1);
	    hm.put("USA-0044-9123", hb2);
	    d.setHealthData(hm);
		System.out.println(gson.toJson(d));
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	CloseableHttpClient client = HttpClientBuilder.create().build();
        	HttpGet getRequest = new HttpGet("http://usa.shoprite.personalization.catalinamarketing.com/proxy/rest/pos/health");
             
             //Set the API media type in http accept header
            getRequest.addHeader("accept", "application/json");
            HttpResponse response = client.execute(getRequest);
            System.out.println("Executing request " + getRequest.getRequestLine());
            System.out.println("----------------------------------------");
            String apiOutput = EntityUtils.toString(response.getEntity());
            System.out.println(apiOutput);
            Type listType = new TypeToken<Map<String, HeartBeatMsg>>() {}.getType();
            Map<String, HeartBeatMsg> temp = new HashMap<String, HeartBeatMsg>();
            temp = gson.fromJson(apiOutput, listType);
            
            System.out.println(temp);
            HeartBeatMsg hbm = temp.get("USA:21:579");
            System.out.println(((System.currentTimeMillis()/1000)-hbm.getTs()));
        } finally {
            httpclient.close();
        }
	}
}
