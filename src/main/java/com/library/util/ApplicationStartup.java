package com.library.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.library.dao.SubscriptionDao;
import com.library.model.Subscription;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private SubscriptionDao subscriptionDao;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		BigDecimal charges = new BigDecimal(20.0);
		Subscription subscription = new Subscription(null, "MONTHLY", charges, 1, 4);
		Long id = subscriptionDao.createSubscription(subscription);
		System.out.println(id);
		charges = new BigDecimal(120.0);
		subscription = new Subscription(null, "YEARLY", charges, 12, 8);
		id = subscriptionDao.createSubscription(subscription);
		System.out.println(id);
	}

}
