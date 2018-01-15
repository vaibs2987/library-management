package com.library.dao;

import java.util.List;
import java.util.Map;

import com.library.model.Book;
import com.library.model.BookingHistory;

public interface BookDao {

	Book addBook(Book book);

	List<Book> getAllBooks();

	Map<Long, Book> getAvailableBookMap();

	Map<Long, Book> getBorrowedBookMap();

	Book getBookById(Long bookId);

	Book removeBookFromMap(Long bookId, boolean isAvailableMap);

	Book addBookToBorrowedMap(BookingHistory bookingHistory);

	void addBookToAvailableMap(Book book);
}
