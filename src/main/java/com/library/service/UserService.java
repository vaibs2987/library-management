package com.library.service;

import com.library.config.PageWrapper;
import com.library.model.Book;
import com.library.model.BookingHistory;

public interface UserService {
	/**
	 * Used for borrowing a book from the system.
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	Book barrowBook(Long bookId, Long userId);

	/**
	 * Used for returning a borrowed book.
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	boolean returnBook(Long bookId, Long userId);

	/**
	 * Get All borrowed book by user with pagination.
	 * 
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 */
	PageWrapper<Book> getAllMyBorrowedBook(Long userId, int page, int size);

	/**
	 * Get All borrowed book history by user with pagination.
	 * 
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 */
	PageWrapper<BookingHistory> getAllBookHistoryByUserId(Long userId, int page, int size);
}
