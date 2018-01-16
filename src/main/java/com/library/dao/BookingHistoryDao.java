package com.library.dao;

import java.util.List;

import com.library.model.BookingHistory;

public interface BookingHistoryDao {

	public BookingHistory addBookHistory(Long userId, Long bookId);

	public void updateBookingHistory(Long userId, Long bookId);

	List<BookingHistory> getAllBookHistoryByBook(Long bookId);
	
	List<BookingHistory> getAllBookHistoryByUser(Long userId);
}
