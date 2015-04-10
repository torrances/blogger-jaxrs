package com.trimc.blogger.jaxrs;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.jaxrs.dao.BooksDao;
import com.trimc.blogger.jaxrs.dto.Book;
import com.trimc.blogger.jaxrs.dto.BookAdapter;
import com.trimc.blogger.jaxrs.util.SpringMongoConfig;

public final class BooksDaoTest {

	@Test
	public void init() throws Throwable {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		assertNotNull(ctx);

		BooksDao booksDao = (BooksDao) ctx.getBean("booksDao");
		assertNotNull(booksDao);
	}

	@Test
	public void remove() throws Throwable {
		@SuppressWarnings("resource") ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		BooksDao booksDao = (BooksDao) ctx.getBean("booksDao");
		booksDao.remove(testBook2()); /* this will succeed whether the object exists or not */
	}

	@Test(expected = BusinessException.class)
	public void save() throws Throwable {
		@SuppressWarnings("resource") ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		BooksDao booksDao = (BooksDao) ctx.getBean("booksDao");
		booksDao.remove(testBook1());
		booksDao.save(testBook1());
		booksDao.save(testBook1());
	}

	@Test
	public void save1() throws Throwable {
		@SuppressWarnings("resource") ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		BooksDao booksDao = (BooksDao) ctx.getBean("booksDao");
		booksDao.remove(testBook1());
		booksDao.save(testBook1());
	}

	private Book testBook1() throws AdapterValidationException {
		Book book = BookAdapter.transform(123l, "Testing for Dummies III", "Me");
		assertNotNull(book);

		return book;
	}

	private Book testBook2() throws AdapterValidationException {
		Book book = BookAdapter.transform("Testing for Dummies III", "Me");
		assertNotNull(book);

		return book;
	}
}
