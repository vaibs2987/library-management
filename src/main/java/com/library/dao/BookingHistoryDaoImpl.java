package com.library.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.config.PropertService;
import com.library.model.BookingHistory;
import com.library.model.BookingStatus;
import com.library.util.HelperService;

@Service
public class BookingHistoryDaoImpl implements BookingHistoryDao {

	@Autowired
	private PropertService propertyService;
	@Autowired
	private HelperService helperService;

	private Map<Long, List<BookingHistory>> bookingMap = new HashMap<>();

	@Override
	public BookingHistory addBookHistory(Long userId, Long bookId) {
		Long currentDate = new Date().getTime();
		Long expDate = currentDate + propertyService.getBookExpiryMilliSeconds();
		BookingHistory bookingHistory = new BookingHistory(bookId, userId, BookingStatus.BOOKED.name(),
				new Date(currentDate), new Date(expDate), null);
		Long bookingId = helperService.getRandomNumuber();
		bookingHistory.setId(bookingId);
		List<BookingHistory> bookingList = bookingMap.get(bookId);
		if (bookingList == null) {
			bookingList = new ArrayList<>();
		}
		bookingList.add(bookingHistory);
		bookingMap.put(bookId, bookingList);
		return bookingHistory;
	}

	@Override
	public void updateBookingHistory(Long userId, Long bookId) {
		List<BookingHistory> bookingList = bookingMap.get(bookId);
		for (BookingHistory history : bookingList) {
			if (history.getUserId().equals(userId) && history.getStatus().equals(BookingStatus.AVAILABLE.name())) {
				history.setActualReleaseDate(new Date());
				history.setStatus(BookingStatus.RELEASED.name());
				bookingMap.put(bookId, bookingList);
				return;
			}
		}

	}

}
