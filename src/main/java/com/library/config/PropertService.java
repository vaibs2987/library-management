package com.library.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "application")
@Component
public class PropertService {

	@Value("${book.expiry.duration}")
	private Long bookExpiryMilliSeconds;

	public Long getBookExpiryMilliSeconds() {
		return bookExpiryMilliSeconds;
	}

	public void setBookExpiryMilliSeconds(Long bookExpiryMilliSeconds) {
		this.bookExpiryMilliSeconds = bookExpiryMilliSeconds;
	}
}
