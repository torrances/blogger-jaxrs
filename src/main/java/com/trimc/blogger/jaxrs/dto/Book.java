package com.trimc.blogger.jaxrs.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {

	private String	author;

	private long	id;

	private String	title;

	public String getAuthor() {
		return author;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		return (getAuthor() + getTitle()).hashCode();
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
