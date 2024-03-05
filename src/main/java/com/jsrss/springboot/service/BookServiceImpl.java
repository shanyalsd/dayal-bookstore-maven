package com.jsrss.springboot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsrss.springboot.entity.Book;
import com.jsrss.springboot.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	static Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	BookRepository repository;
	
	@Override
	public List<Book> getAllBooks() {
		log.info("BookServiceImpl::getAllBooks() starts");
		return repository.findAll();
	}

	@Override
	public Book getBookById(Integer bid) {
		log.info("BookServiceImpl::getBookById() starts");
		return repository.findById(bid).get();
	}

	@Override
	public void addBook(Book book) {
		log.info("BookServiceImpl::addBook() starts");
		repository.save(book);
		log.info("BookServiceImpl::addBook() ends");
	}

	@Override
	public void updateBook(Book book) {
		log.info("BookServiceImpl::updateBook() starts");
		repository.saveAndFlush(book);
		log.info("BookServiceImpl::updateBook() ends");
	}

	@Override
	public void deleteBook(Integer bid) {
		log.info("BookServiceImpl::deleteBook() starts");
		repository.deleteById(bid);
		log.info("BookServiceImpl::deleteBook() ends");
	}
}