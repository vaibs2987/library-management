package com.library.service;

import java.util.List;
import java.util.Set;

import com.library.config.PageWrapper;
import com.library.model.Book;
import com.library.model.BookingHistory;
import com.library.model.User;

public interface LibraryService {

	Long addBookToLibrary(Book book);

	Book getBookById(Long bookId);

	boolean deleteBookFromLibrary(Long bookId);

	Set<Book> getAllAvailableBooksByCategory(Long catgoryId);

	List<Book> searchBookByCategory(String param, Long categoryId);

	List<Book> searchAllBooks(String param);

	PageWrapper<Book> getAllBooks(int page, int size);

	PageWrapper<User> getAllUsers(int page, int size);

	PageWrapper<BookingHistory> getAllBookHistoryByBook(Long bookId, int page, int size);
}
