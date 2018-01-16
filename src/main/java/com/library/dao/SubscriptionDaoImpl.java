package com.library.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.model.Subscription;
import com.library.util.HelperService;

@Repository
public class SubscriptionDaoImpl implements SubscriptionDao {

	@Autowired
	private HelperService helperService;

	private Map<Long, Subscription> subscriptionMap = new HashMap<>();

	@Override
	public Long createSubscription(Subscription subscription) {
		Long id = helperService.getRandomNumuber();
		subscriptionMap.put(id, subscription);
		return id;
	}

	@Override
	public Subscription getSubscriptionDao(Long id) {
		return subscriptionMap.get(id);
	}

}
