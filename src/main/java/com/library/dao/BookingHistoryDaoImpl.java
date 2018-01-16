package com.library.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.config.PropertService;
import com.library.model.BookingHistory;
import com.library.model.BookingStatus;
import com.library.util.HelperService;

@Repository
public class BookingHistoryDaoImpl implements BookingHistoryDao {

	@Autowired
	private PropertService propertyService;
	@Autowired
	private HelperService helperService;

	private Map<Long, List<BookingHistory>> bookingMap = new LinkedHashMap<>();

	private Map<Long, List<BookingHistory>> userBookingMap = new LinkedHashMap<>();

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
		fillUserBookingMap(bookingHistory);
		return bookingHistory;
	}

	private void fillUserBookingMap(BookingHistory bookingHistory) {
		Long userId = bookingHistory.getUserId();
		List<BookingHistory> bookingList = userBookingMap.get(userId);
		if (bookingList == null) {
			bookingList = new ArrayList<>();
		}
		bookingList.add(bookingHistory);
		userBookingMap.put(userId, bookingList);
	}

	@Override
	public void updateBookingHistory(Long userId, Long bookId) {
		List<BookingHistory> bookingList = bookingMap.get(bookId);
		setFlag(userId, bookId, bookingList);
		bookingList = userBookingMap.get(userId);
		setFlag(userId, bookId, bookingList);

	}

	private void setFlag(Long userId, Long bookId, List<BookingHistory> bookingList) {
		for (BookingHistory history : bookingList) {
			if (history.getUserId().equals(userId) && history.getStatus().equals(BookingStatus.BOOKED.name())) {
				history.setActualReleaseDate(new Date());
				history.setStatus(BookingStatus.RELEASED.name());
				bookingMap.put(bookId, bookingList);
				return;
			}
		}
	}

	@Override
	public List<BookingHistory> getAllBookHistoryByBook(Long bookId) {
		return bookingMap.get(bookId);
	}

	@Override
	public List<BookingHistory> getAllBookHistoryByUser(Long userId) {
		return userBookingMap.get(userId);
	}

}
