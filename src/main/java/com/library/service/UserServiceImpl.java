package com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.config.PageWrapper;
import com.library.dao.BookDao;
import com.library.dao.BookingHistoryDao;
import com.library.dao.UserDao;
import com.library.model.Book;
import com.library.model.BookingHistory;
import com.library.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private BookingHistoryDao bookingHistoryDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Book barrowBook(Long bookId, Long userId) {
		Book book = bookDao.getBookById(bookId);
		User user = userDao.getUserById(userId);
		if (book == null || user == null) {
			return null;
		}
		BookingHistory bookingHistory = bookingHistoryDao.addBookHistory(userId, bookId);
		bookDao.removeBookFromMap(bookId, true);
		book = bookDao.addBookToBorrowedMap(bookingHistory);
		userDao.addBookToUserMap(bookId, userId);
		return book;
	}

	@Override
	public boolean returnBook(Long bookId, Long userId) {
		bookingHistoryDao.updateBookingHistory(userId, bookId);
		Book book = bookDao.removeBookFromMap(bookId, false);
		userDao.removeBookFromUserMap(bookId, userId);
		return book != null ? true : false;
	}

	@Override
	public PageWrapper<Book> getAllMyBorrowedBook(Long userId, int page, int size) {
		List<Book> books = userDao.getBooksById(userId);
		return new PageWrapper<>(books, page, size);
	}

	@Override
	public PageWrapper<BookingHistory> getAllBookHistoryByUserId(Long userId, int page, int size) {
		List<BookingHistory> bookingHistories = bookingHistoryDao.getAllBookHistoryByUser(userId);
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
