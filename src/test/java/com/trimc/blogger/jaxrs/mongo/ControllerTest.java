package com.trimc.blogger.jaxrs.mongo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.jaxrs.mongo.dmo.Controller;
import com.trimc.blogger.jaxrs.mongo.dto.BookAdapter;
import com.trimc.blogger.jaxrs.mongo.util.SpringMongoConfig;

public final class ControllerTest {

	public static LogManager	logger	= new LogManager(ControllerTest.class);

	private Controller getController() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		Controller bean = (Controller) ctx.getBean(Controller.class);
		((ConfigurableApplicationContext) ctx).close();

		logger.info("Initialized Controller");
		assertNotNull(bean);
		return bean;
	}

	@Test
	public void post() throws Throwable {
		org.junit.Assume.assumeTrue(SystemUp.check());
		
		getController().post(BookAdapter.transform("wine dark sea", "patrick o'brian"));
	}
}
