package com.library.security;

public interface SubscriptionService {

	public boolean checkSubscription(Long userId);

	public boolean checkSubscriptionExpiry(Long userId);

	public boolean checkSubscrptionBookLimit(Long userId);

	public boolean checkBookAvailabilty(Long bookId);
}
