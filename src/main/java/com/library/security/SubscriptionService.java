package com.library.security;

public interface SubscriptionService {
	/**
	 * Check user has bought the subscription or not.
	 * 
	 * @param userId
	 * @return
	 */
	public boolean checkSubscription(Long userId);

	/**
	 * Check for the expiry of the subscription.
	 * 
	 * @param userId
	 * @return
	 */
	public boolean checkSubscriptionExpiry(Long userId);

	/**
	 * Check subscription book limit.
	 * 
	 * @param userId
	 * @return
	 */
	public boolean checkSubscrptionBookLimit(Long userId);

	/**
	 * Check for availability of the book.
	 * @param bookId
	 * @return
	 */
	public boolean checkBookAvailabilty(Long bookId);
}
