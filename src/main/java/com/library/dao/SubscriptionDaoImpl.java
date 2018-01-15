package com.library.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.model.Subscription;
import com.library.util.HelperService;

@Component
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
	public Map<Long, Subscription> getSubscriptionMap() {
		return subscriptionMap;
	}

	@Override
	public Subscription getSubscriptionDao(Long id) {
		return subscriptionMap.get(id);
	}

}
