package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Book;
import com.library.model.BookingHistory;
import com.library.service.UserService;

@RestController
@RequestMapping({ "/api/library/user/v1" })
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/borrow" }, method = { RequestMethod.GET })
	public Book borrowBook(@RequestParam Long bookId, @RequestParam Long userId) {
		Book book = userService.barrowBook(bookId, userId);
		return book;
	}

	@RequestMapping(value = { "/return" }, method = { RequestMethod.GET })
	public boolean returnBook(@RequestParam Long bookId, @RequestParam Long userId) {
		boolean status = userService.returnBook(bookId, userId);
		return status;
	}

	@RequestMapping(value = { "/all/borrowed" }, method = { RequestMethod.GET })
	public List<Book> getAllBorrowedBooks(@RequestParam Long userId) {
		List<Book> books = userService.getAllMyBorrowedBook(userId);
		return books;
	}

	@RequestMapping(value = { "/all/borrowed/history" }, method = { RequestMethod.GET })
	public List<BookingHistory> getAllBookHistroy(@RequestParam Long userId) {
		List<BookingHistory> books = userService.getAllBookHistoryByUserId(userId);
		return books;
	}

}
