package com.library.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Configuration;

import com.library.model.Book;
import com.library.model.BookingHistory;
import com.library.model.Subscription;
import com.library.model.User;
import com.library.model.UserSubscriptionDetail;

@Configuration
public class DataService {

	private List<Book> books = new ArrayList<>();
	private Map<Long, Book> availableBookMap = new HashMap<>();

	private Map<Long, Book> borrowedBookMap = new HashMap<>();

	private Map<Long, List<BookingHistory>> bookingMap = new HashMap<>();
	private Map<Long, Set<Book>> categoryMap = new HashMap<>();

	// Hold books by user id.
	private Map<Long, List<Book>> userBookMap = new HashMap<>();

	// Hold books by book id
	private Map<Long, Book> userSubscriptionMap = new HashMap<>();

	// Hold the user data by id
	private Map<Long, User> userMap = new HashMap<>();

	private Map<Long, Subscription> subscriptionmap = new HashMap<>();

	private Map<Long, UserSubscriptionDetail> userSubDeatilMap = new HashMap<>();

}
