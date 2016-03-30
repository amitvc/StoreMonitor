package com.catmktg.monitoring.StoreMonitor;

import java.io.IOException;

import com.catmktg.monitoring.StoreMonitor.analyzer.BasicHealthAnalyzer;
import com.catmktg.monitoring.StoreMonitor.analyzer.StoreDataAnalyzer;
import com.catmktg.monitoring.StoreMonitor.config.RetailerApiConfig;


/**
 * Hello world!
 *
 */
public class App {
	public static final String shoprite_health_api = "http://usa.shoprite.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String riteaid_health_api = "http://usa.riteaid.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String walgreens_health_api = "http://usa.walgreens.personalization.catalinamarketing.com/proxy/rest/pos/health";
	public static final String shared_health_api = "http://usa.shared.personalization.catalinamarketing.com/proxy/rest/pos/health";
	
	public static void main(String[] args) throws IOException {
		
		RetailerApiConfig shopRiteConfig = new RetailerApiConfig();
		shopRiteConfig.setRetailerName("ShopRite");
		shopRiteConfig.setHealthApi(shoprite_health_api);
		
		RetailerApiConfig riteAidConfig = new RetailerApiConfig();
		riteAidConfig.setRetailerName("RiteAid");
		riteAidConfig.setHealthApi(riteaid_health_api);
		
		RetailerApiConfig walgreensConfig = new RetailerApiConfig();
		walgreensConfig.setRetailerName("Walgreens");
		walgreensConfig.setHealthApi(walgreens_health_api);
		
		RetailerApiConfig sharedConfig = new RetailerApiConfig();
		sharedConfig.setRetailerName("Shared");
		sharedConfig.setHealthApi(shared_health_api);
		
		StoreDataAnalyzer analyzer = new BasicHealthAnalyzer();
		analyzer.analyze(shopRiteConfig);
		analyzer.analyze(walgreensConfig);
		analyzer.analyze(sharedConfig);
		
	}
}
