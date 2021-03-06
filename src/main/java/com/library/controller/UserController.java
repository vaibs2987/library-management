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
import com.library.model.Book;
import com.library.model.BookingHistory;
import com.library.service.UserService;

/**
 * 
 * Class holds all the user related APIS. In all the APIS where I am passing
 * userId should actually come from session but here I am not maintaining any
 * session, so it is coming as parameter from the client.
 *
 */
@RestController
@RequestMapping({ "/api/library/user/v1" })
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * API responsible for borrowing a book. Aspect is applied on it for
	 * checking validation.
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = { "/borrow" }, method = { RequestMethod.GET })
	public ResponseEntity<Book> borrowBook(@RequestParam @NotNull Long bookId, @RequestParam @NotNull Long userId) {
		Book book = userService.barrowBook(bookId, userId);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@RequestMapping(value = { "/return" }, method = { RequestMethod.GET })
	public ResponseEntity<Boolean> returnBook(@RequestParam Long bookId, @RequestParam Long userId) {
		boolean status = userService.returnBook(bookId, userId);
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}

	@RequestMapping(value = { "/all/borrowed" }, method = { RequestMethod.GET })
	public ResponseEntity<PageWrapper<Book>> getAllBorrowedBooks(@RequestParam Long userId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
		PageWrapper<Book> books = userService.getAllMyBorrowedBook(userId, page, size);
		return new ResponseEntity<PageWrapper<Book>>(books, HttpStatus.OK);
	}

	@RequestMapping(value = { "/all/borrowed/history" }, method = { RequestMethod.GET })
	public ResponseEntity<PageWrapper<BookingHistory>> getAllBookHistroy(@RequestParam Long userId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
		PageWrapper<BookingHistory> pages = userService.getAllBookHistoryByUserId(userId, page, size);
		return new ResponseEntity<PageWrapper<BookingHistory>>(pages, HttpStatus.OK);
	}

}
