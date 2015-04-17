package com.trimc.blogger.jaxrs.mongo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.jaxrs.mongo.dto.Book;

@Component
@ComponentScan
public class BooksDao {

	public static LogManager	logger	= new LogManager(BooksDao.class);

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate		mongoOperations;

	public Book find(Book book) throws BusinessException {
		if (null != book) return find(book.getId());
		return null;
	}

	public Book find(Long id) throws BusinessException {
		Book book = mongoOperations.findById(id, Book.class);

		if (null != book) logger.info("Found book (id = %s)", id);
		else logger.info("Could not find book (id = %s)", id);

		return book;
	}

	public void remove(Book book) throws BusinessException {
		mongoOperations.remove(book);
		logger.info("Removed book (id = %s)", book.getId());
	}

	public void replace(Book book) throws BusinessException {

		Book _book = find(book);
		if (null == _book) {

			save(book);
			return;
		}

		boolean isIdentical = _book.hashCode() == book.hashCode();
		if (isIdentical) {

			logger.info("An identical object already exists (type = book, id = %s)", book.getId());
			return;
		}

		remove(_book);
		save(book);
	}

	public void save(Book book) throws BusinessException {
		try {

			mongoOperations.insert(book);
			logger.info("Saved book (id = %s)", book.getId());

		} catch (DuplicateKeyException e) {
			logger.error(e, "Duplicate Key Exception (type = book, id = %s)", book.getId());
			throw new BusinessException("Object not inserted (type = book, id = %s, reason = already exists)", book.getId());
		}
	}
}
