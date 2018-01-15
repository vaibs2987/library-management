package com.library.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.model.Subscription;
import com.library.model.UserSubscriptionDetail;
import com.library.util.HelperService;

@Component
public class UserSubscriptionDaoImpl implements UserSubscriptionDao {

	@Autowired
	private HelperService helperService;

	@Autowired
	private SubscriptionDao subscriptionDao;

	private Map<Long, UserSubscriptionDetail> userSubDeatilMap = new HashMap<>();

	@Override
	public Long buySubscription(Long subscriptionId, Long userId) {
		UserSubscriptionDetail userSub = createObj(subscriptionId, userId);
		userSubDeatilMap.put(userSub.getId(), userSub);
		return userSub.getId();
	}

	@Override
	public UserSubscriptionDetail getUserSubsciption(Long subscriptionId) {
		return userSubDeatilMap.get(subscriptionId);
	}

	private UserSubscriptionDetail createObj(Long subscriptionId, Long userId) {
		UserSubscriptionDetail userSub = new UserSubscriptionDetail();
		Long userSubsciptionId = helperService.getRandomNumuber();
		Subscription subscription = subscriptionDao.getSubscriptionMap().get(subscriptionId);
		userSub.setId(userSubsciptionId);
		Date currentDate = new Date();
		userSub.setStartDate(currentDate);
		userSub.setEndDate(helperService.extendCurrentDate(subscription.getDurationInMonths()));
		userSub.setSubscriptionId(subscriptionId);
		userSub.setUserId(userId);
		return userSub;
	}

}
