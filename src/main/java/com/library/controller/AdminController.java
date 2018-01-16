package com.library.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	private User getUser(@RequestParam @NotNull Long id) {
		return userDao.getUserById(id);
	}

	@RequestMapping(value = { "/all" }, method = { RequestMethod.GET })
	private List<User> getUser() {
		return userDao.getAllUsers();
	}

	@RequestMapping(value = { "/all/borrowed/history" }, method = { RequestMethod.GET })
	public List<BookingHistory> getAllBookHistroy(@RequestParam Long bookId) {
		List<BookingHistory> books = libraryService.getAllBookHistoryByBook(bookId);
		return books;
	}

	@RequestMapping(value = { "/book/all" }, method = { RequestMethod.GET })
	private List<Book> getAllBooks() {
		return libraryService.getAllBooks();
	}

}
