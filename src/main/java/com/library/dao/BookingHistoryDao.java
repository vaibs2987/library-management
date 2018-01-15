package com.library.dao;

import com.library.model.BookingHistory;

public interface BookingHistoryDao {

	public BookingHistory addBookHistory(Long userId, Long bookId);

	public void updateBookingHistory(Long userId, Long bookId);
}
