package com.library.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Book;
import com.library.service.LibraryService;

@RestController
@RequestMapping({ "/api/library/v1" })
public class LibraryController {
	@Autowired
	private LibraryService libraryService;

	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	private Long addBook(@RequestParam @NotNull Long id) {
		Book book = new Book();
		return libraryService.addBookToLibrary(book);
	}

	@RequestMapping(value = { "" }, method = { RequestMethod.GET })
	private Book getBook(@RequestParam @NotNull Long id) {
		return libraryService.getBookById(id);
	}
}
