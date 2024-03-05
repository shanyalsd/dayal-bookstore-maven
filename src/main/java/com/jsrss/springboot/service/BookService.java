package com.jsrss.springboot.service;

import java.util.List;

import com.jsrss.springboot.entity.Book;

public interface BookService {

	public List<Book> getAllBooks();
	public Book getBookById(Integer bid);
	public void addBook(Book book);
	public void updateBook(Book book);
	public void deleteBook(Integer bid);
}
