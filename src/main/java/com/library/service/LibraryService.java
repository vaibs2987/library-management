package com.library.service;

import com.library.config.PageWrapper;
import com.library.model.Book;
import com.library.model.BookingHistory;
import com.library.model.User;

public interface LibraryService {
	/**
	 * Add a book to the system.
	 * 
	 * @param book
	 * @return
	 */
	Long addBookToLibrary(Book book);

	/**
	 * Get book by id.
	 * 
	 * @param bookId
	 * @return
	 */
	Book getBookById(Long bookId);

	/**
	 * Get All the books by pagination.
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	PageWrapper<Book> getAllBooks(int page, int size);

	/**
	 * Get All the users by pagination.
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	PageWrapper<User> getAllUsers(int page, int size);

	/**
	 * Get all booking history of a book.
	 * 
	 * @param bookId
	 * @param page
	 * @param size
	 * @return
	 */
	PageWrapper<BookingHistory> getAllBookHistoryByBook(Long bookId, int page, int size);
}
