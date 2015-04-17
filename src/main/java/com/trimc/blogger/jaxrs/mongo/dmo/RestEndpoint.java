package com.trimc.blogger.jaxrs.mongo.dmo;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.gson.JsonSyntaxException;
import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.jaxrs.mongo.dto.Book;
import com.trimc.blogger.jaxrs.mongo.dto.BookAdapter;
import com.trimc.blogger.jaxrs.mongo.dto.ResponseAdapter;
import com.trimc.blogger.jaxrs.mongo.util.SpringMongoConfig;

@Path("/endpoint")
public class RestEndpoint {

	public static LogManager	logger	= new LogManager(RestEndpoint.class);

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Object get1(@QueryParam("id") Long id) {
		 return getController().get(id);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object get2(@PathParam("id") Long id) {
		return get1(id);
	}

	private Controller getController() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		Controller bean = (Controller) ctx.getBean(Controller.class);
		((ConfigurableApplicationContext) ctx).close();

		logger.info("Initialized Controller");
		return bean;
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post1(Book book) {
		logger.info("Invoked Post Method 1:\n\t%s", BookAdapter.toString(book));
		return getController().post(book);
	}

	@POST
	@Path("/form")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response post2(@FormParam("id") long id, @FormParam("author") String author, @FormParam("title") String title) {
		logger.info("Invoked Post Method 2:\n\tid = %s, author = %s, title = %s", id, author, title);
		return post1(BookAdapter.transform(id, title, author));
	}

	@POST
	@Path("/{param}")
	public Response post3(@PathParam("param") String msg) {
		logger.info("Invoked Post Method 3:\n\t%s", msg);
		try {

			Book book = GsonUtils.toObject(msg, Book.class);
			if (null != book) return post1(book);

		} catch (JsonSyntaxException e) {
			logger.error(e, "Invalid JSON (%s)", msg);
		}

		return ResponseAdapter.postFail("book", msg);
	}
}
