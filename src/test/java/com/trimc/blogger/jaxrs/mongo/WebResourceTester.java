package com.trimc.blogger.jaxrs.mongo;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.jaxrs.mongo.dto.Book;
import com.trimc.blogger.jaxrs.mongo.dto.BookAdapter;

public class WebResourceTester {

	public static LogManager	logger	= new LogManager(WebResourceTester.class);

	public static final String	URL		= "http://192.168.1.7:8080/blogger/demo/endpoint/";

	public static void main(String... args) throws Throwable {

		Client client = Client.create();
		Book book = BookAdapter.transform(12345l, "Nutmeg of Consolation", "Patrick O'Brian");

		WebResource webResourcePost = client.resource(URL + "post");
		ClientResponse responsePost = webResourcePost.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, book);

		logger.info("%s", responsePost.getEntity(String.class));

		WebResource webResourceGet = client.resource(URL + book.getId());
		ClientResponse responseGet = webResourceGet.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		Book _book = responseGet.getEntity(Book.class);
		assertEquals(book.hashCode(), _book.hashCode());
	}
}
