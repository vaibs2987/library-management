package com.library.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.config.PageWrapper;
import com.library.dao.BookDao;
import com.library.dao.BookingHistoryDao;
import com.library.dao.UserDao;
import com.library.model.Book;
import com.library.model.BookingHistory;
import com.library.model.User;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private UserDao userDao;

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
	public PageWrapper<Book> getAllBooks(int page, int size) {
		List<Book> books = bookDao.getAllBooks();
		PageWrapper<Book> pages = new PageWrapper<Book>(books, page, size);
		return pages;
	}

	@Override
	public PageWrapper<User> getAllUsers(int page, int size) {
		List<User> users = userDao.getAllUsers();
		PageWrapper<User> pages = new PageWrapper<User>(users, page, size);
		return pages;
	}

	@Override
	public PageWrapper<BookingHistory> getAllBookHistoryByBook(Long bookId, int page, int size) {
		List<BookingHistory> bookingHistories = bookingHistoryDao.getAllBookHistoryByBook(bookId);
		if (bookingHistories == null) {
			return null;
		}
		PageWrapper<BookingHistory> pages = new PageWrapper<>(bookingHistories, page, size);
		for (BookingHistory bookingHistory : pages.getItems()) {
			bookingHistory.setBook(bookDao.getBookById(bookingHistory.getBookId()));
		}
		return pages;
	}

}
