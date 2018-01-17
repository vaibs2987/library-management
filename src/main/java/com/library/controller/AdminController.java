package com.library.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.config.PageWrapper;
import com.library.dao.UserDao;
import com.library.model.Book;
import com.library.model.BookingHistory;
import com.library.model.User;
import com.library.service.LibraryService;

@RestController
@RequestMapping({ "/api/library/admin/v1" })
public class AdminController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private LibraryService libraryService;

	@RequestMapping(value = { "" }, method = { RequestMethod.GET })
	private ResponseEntity<User> getUser(@RequestParam @NotNull Long id) {
		ResponseEntity<User> userRespone = new ResponseEntity<User>(userDao.getUserById(id), HttpStatus.OK);
		return userRespone;
	}

	@RequestMapping(value = { "/all" }, method = { RequestMethod.GET })
	private ResponseEntity<PageWrapper<User>> getUser(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size) {
		PageWrapper<User> pages = libraryService.getAllUsers(page, size);
		return new ResponseEntity<PageWrapper<User>>(pages, HttpStatus.OK);
	}

	@RequestMapping(value = { "/all/borrowed/history" }, method = { RequestMethod.GET })
	public ResponseEntity<PageWrapper<BookingHistory>> getAllBookHistroy(@RequestParam Long bookId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
		PageWrapper<BookingHistory> books = libraryService.getAllBookHistoryByBook(bookId, page, size);
		return new ResponseEntity<PageWrapper<BookingHistory>>(books, HttpStatus.OK);
	}

	@RequestMapping(value = { "/book/all" }, method = { RequestMethod.GET })
	private ResponseEntity<PageWrapper<Book>> getAllBooks(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size) {
		PageWrapper<Book> pages = libraryService.getAllBooks(page, size);
		return new ResponseEntity<PageWrapper<Book>>(pages, HttpStatus.OK);
	}

}
