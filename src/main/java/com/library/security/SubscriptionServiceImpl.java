package com.library.security;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.config.DataService;
import com.library.dao.BookDao;
import com.library.dao.SubscriptionDao;
import com.library.dao.UserDao;
import com.library.dao.UserSubscriptionDao;
import com.library.model.Book;
import com.library.model.Subscription;
import com.library.model.User;
import com.library.model.UserSubscriptionDetail;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private DataService dataService;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserSubscriptionDao userSubscriptionDao;

	@Autowired
	private SubscriptionDao subscriptionDao;

	@Override
	public boolean checkSubscription(Long userId) {
		Map<Long, User> userMap = userDao.getUserMap();
		User user = userMap.get(userId);
		return (user != null && user.getCurrentSubscriptionDetailId() != null) ? true : false;
	}

	@Override
	public boolean checkSubscriptionExpiry(Long userId) {
		Map<Long, User> userMap = userDao.getUserMap();
		User user = userMap.get(userId);
		Long subscrptionId = user.getCurrentSubscriptionDetailId();
		UserSubscriptionDetail userSubDetail = userSubscriptionDao.getUserSubsciption(subscrptionId);
		return (userSubDetail.getStatus().equalsIgnoreCase("ACTIVE")
				&& userSubDetail.getEndDate().getTime() > new Date().getTime()) ? true : false;

	}

	@Override
	public boolean checkSubscrptionBookLimit(Long userId) {
		Map<Long, User> userMap = userDao.getUserMap();
		User user = userMap.get(userId);
		if (user == null) {
			return false;
		}
		Long subscrptionId = user.getCurrentSubscriptionDetailId();
		Subscription subscription = subscriptionDao.getSubscriptionDao(subscrptionId);
		int maxBookLimit = subscription.getBookLimit();
		int userBookCount = userDao.getBookCountByUser(userId);
		return maxBookLimit >= userBookCount ? true : false;
	}

	@Override
	public boolean checkBookAvailabilty(Long bookId) {
		Book book = bookDao.getAvailableBookMap().get(bookId);
		return book != null ? true : false;
	}

}
