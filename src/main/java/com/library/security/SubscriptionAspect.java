package com.library.security;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.security.exception.AccessDeniedExpection;

@Aspect
@Component
public class SubscriptionAspect {

	@Autowired
	private SubscriptionService subscriptionService;

	@Before("execution(* com.library.controller.UserController.borrowBook(..))")
	public void checkUserSubscrption(JoinPoint joinPoint) {
		Long userId = (long) joinPoint.getArgs()[1];
		if (!subscriptionService.checkSubscription(userId)) {
			throw new AccessDeniedExpection("User does not have any subscription");
		}

		if (!subscriptionService.checkSubscriptionExpiry(userId)) {
			throw new AccessDeniedExpection("User subscription expires.");
		}

		if (!subscriptionService.checkSubscrptionBookLimit(userId)) {
			throw new AccessDeniedExpection("User subscription crosses book limit");
		}

	}
}
