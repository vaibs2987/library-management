package com.library.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dao.BookDao;
import com.library.dao.BookingHistoryDao;
import com.library.model.Book;
import com.library.model.BookingHistory;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private BookingHistoryDao bookingHistoryDao;

	public Long addBookToLibrary(Book book) {
		book = bookDao.addBook(book);
		return book.getId();
	}

	public Book getBookById(Long bookId) {
		return bookDao.getBookById(bookId);
	}

	public boolean deleteBookFromLibrary(Long bookId) {
		return false;
	}

	public Set<Book> getAllAvailableBooksByCategory(Long catgoryId) {

		return null;
	}

	public List<Book> searchBookByCategory(String param, Long categoryId) {

		return null;
	}

	public List<Book> searchAllBooks(String param) {

		return null;
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	@Override
	public List<BookingHistory> getAllBookHistoryByBook(Long bookId) {
		List<BookingHistory> bookingHistories = bookingHistoryDao.getAllBookHistoryByBook(bookId);
		if (bookingHistories == null) {
			return null;
		}
		for (BookingHistory bookingHistory : bookingHistories) {
			bookingHistory.setBook(bookDao.getBookById(bookingHistory.getBookId()));
		}
		return bookingHistories;
	}

}
