package com.trimc.blogger.jaxrs.dmo;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.jaxrs.dao.BooksDao;
import com.trimc.blogger.jaxrs.dto.Book;
import com.trimc.blogger.jaxrs.dto.BookAdapter;
import com.trimc.blogger.jaxrs.dto.ResponseAdapter;

@Component
public class Controller {

	public static LogManager	logger	= new LogManager(Controller.class);

	@Autowired
	private BooksDao			booksDao;

	public Response get(Long id) {
		try {

			Book book = booksDao.find(id);
			if (null != book) return ResponseAdapter.getSuccess(book);

		} catch (BusinessException e) {
			logger.error(e);
		}

		return ResponseAdapter.getFail("book", id);
	}

	public Response post(Book book) {
		logger.info("Received Request (P3):\n\t%s", BookAdapter.toString(book));

		try {

			booksDao.replace(book);
			return ResponseAdapter.postSuccess("book", book.getId());

		} catch (BusinessException e) {
			logger.error(e);
		}

		logger.info("Failed to post the book");
		return ResponseAdapter.postFail("book", (null != book) ? book.getId() : -1);
	}
}
