package com.trimc.blogger.jaxrs.dto;

import com.trimc.blogger.jaxrs.util.UidGenerator;

public final class BookAdapter {

	public static String toString(Book book) {
		if (null == book) return "null";

		StringBuilder sb = new StringBuilder();

		sb.append("id = ");
		sb.append(book.getId());
		sb.append(", title = ");
		sb.append(book.getTitle());
		sb.append(", author = ");
		sb.append(book.getAuthor());

		return sb.toString();
	}

	public static Book transform(Long id, String title, String author) {
		Book document = new Book();

		document.setId(id);
		document.setTitle(title);
		document.setAuthor(author);

		return document;
	}

	public static Book transform(String title, String author) {
		return transform(UidGenerator.id(), title, author);
	}
}
