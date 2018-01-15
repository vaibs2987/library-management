package com.library.service;

import java.util.List;

import com.library.model.Book;

public interface UserService {

	Book barrowBook(Long bookId, Long userId);

	boolean returnBook(Long bookId, Long userId);

	List<Book> getAllMyBook(Long userId);
}
