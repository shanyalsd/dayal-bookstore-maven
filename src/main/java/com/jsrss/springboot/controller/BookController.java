package com.jsrss.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jsrss.springboot.entity.Book;
import com.jsrss.springboot.service.BookService;

@Controller
@SessionAttributes("MyBooksList") 
public class BookController {

	static Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookService service;
	
	@GetMapping("/showAllBooks")
	public String showBooksList(Model model) {
		log.info("-------BookController--showBooksList()---------");
		List<Book> blist = service.getAllBooks();
		model.addAttribute("MyBooksList", blist);
		return "booksList";
	}
	
	@PostMapping("/addEditBookForm")
	public String addBookForm(@RequestParam("bookId") Integer bookId, Model model) {
		log.info("-------BookController--addEditBookForm()---------");
		log.debug("Book Id from request param : {} ", bookId);
		Book book = new Book();
		String opType = "ADD";
		if (bookId != 0) {
			book = service.getBookById(bookId);
			opType = "UPDATE";
		}
		model.addAttribute("mybook", book);
		model.addAttribute("OpType", opType);
		return "addEditBook";
	}
	
	@PostMapping("/saveUpdateBook")
	public String saveUpdateBook(@Valid @ModelAttribute("mybook") Book book, BindingResult result, Model model,
			HttpServletRequest req) {
		log.info("-------BookController--saveUpdateBook()---------");
		String opType = req.getParameter("OpType");
		System.out.println(opType);
		if (opType.equals("ADD")) {
			service.addBook(book);
		}
		if (opType.equals("UPDATE")) {
			service.updateBook(book);
		}
		List<Book> blist = service.getAllBooks();
		model.addAttribute("MyBooksList", blist);
		return "booksList";
	}
	
	@PostMapping("/deleteBook")
	public String deleteBook(@RequestParam("bookId") Integer bookId, Model model) {
		log.info("-------BookController--deleteBook()---------");
		log.debug("Book Id from request param : {} ", bookId);
		service.deleteBook(bookId);
		List<Book> blist = service.getAllBooks();
		model.addAttribute("MyBooksList", blist);
		return "booksList";
	}
	
	@GetMapping("/viewBook")
	public String viewBook(@RequestParam("bookId") String bookId, Model model) {
		log.info("-------BookController--viewBook()---------");
		log.debug("Book Id from request param : {} ", bookId);
		Book book = service.getBookById(Integer.parseInt(bookId));
		model.addAttribute("MyBook", book);
		return "viewBook";
	}
}