package com.library.service;

import com.library.config.PageWrapper;
import com.library.model.Book;
import com.library.model.BookingHistory;

public interface UserService {

	Book barrowBook(Long bookId, Long userId);

	boolean returnBook(Long bookId, Long userId);

	PageWrapper<Book> getAllMyBorrowedBook(Long userId, int page, int size);

	PageWrapper<BookingHistory> getAllBookHistoryByUserId(Long userId, int page, int size);
}
