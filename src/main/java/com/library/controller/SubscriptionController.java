package com.library.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dao.SubscriptionDao;
import com.library.model.Subscription;

@RestController
@RequestMapping({ "/api/library/subscription/v1" })
public class SubscriptionController {

	@Autowired
	private SubscriptionDao subscriptionDao;

	@RequestMapping(value = { "" }, method = { RequestMethod.GET })
	private ResponseEntity<Subscription> getSubscription(@RequestParam @NotNull Long id) {
		return new ResponseEntity<Subscription>(subscriptionDao.getSubscriptionDao(id), HttpStatus.OK);
	}
}
