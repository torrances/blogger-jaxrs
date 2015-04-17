package com.trimc.blogger.jaxrs;

import static org.junit.Assert.assertNotNull;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.trimc.blogger.jaxrs.util.AppConfig;
import com.trimc.blogger.jaxrs.util.SpringMongoConfig;

public final class SystemTest {

	private static AppConfig getAppConfig() throws Exception {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		assertNotNull(ctx);

		AppConfig appConfig = (AppConfig) ctx.getBean("appConfig");
		assertNotNull(appConfig);

		return appConfig;
	}

	private static int getStatus(URL url) {
		try {
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			huc.setRequestMethod("GET"); //OR  huc.setRequestMethod ("HEAD"); 
			huc.connect();
			return huc.getResponseCode();
		} catch (Exception e) {
			return 404;
		}
	}

	public static void up() throws Exception {

		String host = getAppConfig().getHost();
		Integer port = getAppConfig().getPort();

		if (404 == getStatus(new URL("http://" + host))) throw new Exception(String.format("System Not Up (%s)", host));
		if (404 == getStatus(new URL("http://" + host + ":" + port))) throw new Exception(String.format("System Not Up (%s)", host));
	}
}
