package com.library.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.library.model.Subscription;

@Service
public class HelperService {
	Random rand = new Random();

	public Long getRandomNumuber() {
		return rand.nextLong();
	}

	public Date extendCurrentDate(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	public Subscription createSubscription(String name, BigDecimal charges, 
			int durationInMonths, int bookLimit) {
		Subscription subscription = new Subscription(getRandomNumuber(), name, charges, durationInMonths, bookLimit);
		return subscription;
	}
}
