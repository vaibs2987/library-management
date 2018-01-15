package com.library.security;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SubscriptionAspect {

	@Autowired
	private SubscriptionService subscriptionService;

	public void checkUserSubscrption(JoinPoint joinPoint) {
		Long userId = (long) joinPoint.getArgs()[0];
		if (!subscriptionService.checkSubscription(userId)) {
			throw new RuntimeException("User does not have any subscription");
		}

		if (!subscriptionService.checkSubscriptionExpiry(userId)) {
			throw new RuntimeException("User subscription expires.");
		}

		if (!subscriptionService.checkSubscrptionBookLimit(userId)) {
			throw new RuntimeException("User subscription crosses book limit");
		}

	}
}
