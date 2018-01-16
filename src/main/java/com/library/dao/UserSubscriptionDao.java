package com.library.dao;

import com.library.model.UserSubscriptionDetail;

public interface UserSubscriptionDao {

	public Long buySubscription(Long subscriptionId, Long userId);
	
	public UserSubscriptionDetail getUserSubsciption(Long subscriptionDetailId);
}
