package com.trimc.blogger.jaxrs.mongo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public final class MongoClientTest {

	@Test
	public void run() throws Throwable {
		org.junit.Assume.assumeTrue(SystemUp.check());

		MongoClient mongoClient = new MongoClient(SystemUp.getConfig().getHost(), SystemUp.getConfig().getPort());
		assertNotNull(mongoClient);

		DB db = mongoClient.getDB("testdb");
		assertNotNull(db);

		DBCollection coll = db.getCollection("testCollection");
		assertNotNull(coll);

		coll.drop();

		BasicDBObject document = new BasicDBObject();
		document.put("content", "this is the document content");

		coll.remove(document);
		coll.insert(document);
		assertNotNull(coll.findOne());
	}
}
