package com.library.dao;

import com.library.model.Subscription;

public interface SubscriptionDao {

	Long createSubscription(Subscription subscription);

	Subscription getSubscriptionDao(Long id);
}
