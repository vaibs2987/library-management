package com.library.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private BookDao bookDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserSubscriptionDao userSubscriptionDao;

	@Autowired
	private SubscriptionDao subscriptionDao;

	@Override
	public boolean checkSubscription(Long userId) {
		User user = userDao.getUserById(userId);
		return (user != null && user.getCurrentSubscriptionDetailId() != null) ? true : false;
	}

	@Override
	public boolean checkSubscriptionExpiry(Long userId) {
		User user = userDao.getUserById(userId);
		Long subscrptionId = user.getCurrentSubscriptionDetailId();
		UserSubscriptionDetail userSubDetail = userSubscriptionDao.getUserSubsciption(subscrptionId);
		return (userSubDetail.getStatus().equalsIgnoreCase("ACTIVE")
				&& userSubDetail.getEndDate().getTime() > new Date().getTime()) ? true : false;

	}

	@Override
	public boolean checkSubscrptionBookLimit(Long userId) {
		User user = userDao.getUserById(userId);
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
		Book book = bookDao.getBookFromMap(bookId, true);
		if (book == null) {
			return false;
		}
		int availableQuantity = book.getCurrnetQuantity();
		int totalQuantity = book.getTotalQuanity();
		return totalQuantity - availableQuantity > 0;
	}

}
