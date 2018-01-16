package com.library.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.model.Subscription;
import com.library.util.HelperService;

@Repository
public class SubscriptionDaoImpl implements SubscriptionDao {

	@Autowired
	private HelperService helperService;

	private Map<Long, Subscription> subscriptionMap = new LinkedHashMap<>();

	@Override
	public Long createSubscription(Subscription subscription) {
		Long id = subscription.getId();
		if (id == null) {
			id = helperService.getRandomNumuber();
		}
		subscription.setId(id);
		subscriptionMap.put(id, subscription);
		return id;
	}

	@Override
	public Subscription getSubscriptionDao(Long id) {
		return subscriptionMap.get(id);
	}

}
