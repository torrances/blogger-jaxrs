package com.trimc.blogger.jaxrs;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public final class MongoClientTest {

	@Test
	public void run() throws Throwable {

		MongoClient mongoClient = new MongoClient("192.168.1.26", 27017);
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
