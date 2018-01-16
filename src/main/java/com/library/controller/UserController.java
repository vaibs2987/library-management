package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Book;
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
}
