package com.library.dao;

import java.util.Map;

import com.library.model.Subscription;

public interface SubscriptionDao {

	Long createSubscription(Subscription subscription);

	Map<Long, Subscription> getSubscriptionMap();

	Subscription getSubscriptionDao(Long id);
}
