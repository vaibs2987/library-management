package com.library.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class HelperService {
	Random random = new Random();

	public Long getRandomNumuber() {
		Long id = (long) random.nextInt(Integer.MAX_VALUE);
		return id;
	}

	public Date extendCurrentDate(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

}
