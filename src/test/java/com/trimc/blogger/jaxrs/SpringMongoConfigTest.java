package com.trimc.blogger.jaxrs;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.trimc.blogger.jaxrs.dto.Book;
import com.trimc.blogger.jaxrs.dto.BookAdapter;
import com.trimc.blogger.jaxrs.util.SpringMongoConfig;

public final class SpringMongoConfigTest {

	@Test
	public void run() throws Throwable {
		org.junit.Assume.assumeTrue(SystemUp.check());

		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		assertNotNull(ctx);

		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		assertNotNull(mongoOperation);

		Book vizdoc = BookAdapter.transform(111l, "the test title", "a testing author");
		assertNotNull(vizdoc);

		/* does not exist ... */
		assertNull(mongoOperation.findById(vizdoc.getId(), Book.class));

		mongoOperation.insert(vizdoc);

		/* must exist ... */
		assertNotNull(mongoOperation.findById(vizdoc.getId(), Book.class));

		mongoOperation.remove(vizdoc);

		/* does not exist ... */
		assertNull(mongoOperation.findById(vizdoc.getId(), Book.class));
	}
}
