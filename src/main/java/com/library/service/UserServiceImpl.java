package com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dao.BookDao;
import com.library.dao.BookingHistoryDao;
import com.library.dao.UserDao;
import com.library.model.Book;
import com.library.model.BookingHistory;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BookingHistoryDao bookingHistoryDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Book barrowBook(Long bookId, Long userId) {
		BookingHistory bookingHistory = bookingHistoryDao.addBookHistory(userId, bookId);
		bookDao.removeBookFromMap(bookId, true);
		Book book = bookDao.addBookToBorrowedMap(bookingHistory);
		userDao.addBookToUserMap(bookId, userId);
		return book;
	}

	@Override
	public boolean returnBook(Long bookId, Long userId) {
		bookingHistoryDao.updateBookingHistory(userId, bookId);
		bookDao.removeBookFromMap(bookId, false);
		userDao.removeBookFromUserMap(bookId, userId);
		return false;
	}

	@Override
	public List<Book> getAllMyBorrowedBook(Long userId) {
		return userDao.getBooksById(userId);
	}

	@Override
	public List<BookingHistory> getAllBookHistoryByUserId(Long userId) {
		List<BookingHistory> bookingHistories = bookingHistoryDao.getAllBookHistoryByUser(userId);
		if (bookingHistories == null) {
			return null;
		}
		for (BookingHistory bookingHistory : bookingHistories) {
			bookingHistory.setBook(bookDao.getBookById(bookingHistory.getBookId()));
		}
		return bookingHistories;
	}
}
