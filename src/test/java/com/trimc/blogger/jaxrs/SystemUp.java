package com.trimc.blogger.jaxrs;

import static org.junit.Assert.assertNotNull;

import java.net.Socket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.trimc.blogger.jaxrs.util.AppConfig;
import com.trimc.blogger.jaxrs.util.SpringMongoConfig;

public final class SystemUp {

	public static boolean check() throws Throwable {

		return serverListening(getConfig().getHost(), getConfig().getPort());
	}

	public static AppConfig getConfig() throws Throwable {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		assertNotNull(ctx);

		AppConfig appConfig = (AppConfig) ctx.getBean("appConfig");
		assertNotNull(appConfig);

		return appConfig;
	}

	/* dc:url <http://www.geekality.net/2013/04/30/java-simple-check-to-see-if-a-server-is-listening-on-a-port/> */
	private static boolean serverListening(String host, int port) {
		Socket s = null;
		try {
			s = new Socket(host, port);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (s != null) try {
				s.close();
			} catch (Exception e) {}
		}
	}
}
