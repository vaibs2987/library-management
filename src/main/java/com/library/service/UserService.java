package com.library.service;

import java.util.List;

import com.library.model.Book;
import com.library.model.BookingHistory;

public interface UserService {

	Book barrowBook(Long bookId, Long userId);

	boolean returnBook(Long bookId, Long userId);

	List<Book> getAllMyBorrowedBook(Long userId);

	List<BookingHistory> getAllBookHistoryByUserId(Long userId);
}
