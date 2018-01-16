package com.library.model;

import java.math.BigDecimal;

public class Subscription {

	private Long id;
	private String name;
	private BigDecimal charges;
	private int durationInMonths;
	private int bookLimit;

	public Subscription(Long id, String name, BigDecimal charges, int durationInMonths, int bookLimit) {
		this.id = id;
		this.name = name;
		this.charges = charges;
		this.durationInMonths = durationInMonths;
		this.bookLimit = bookLimit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getDurationInMonths() {
		return durationInMonths;
	}

	public BigDecimal getCharges() {
		return charges;
	}

	public int getBookLimit() {
		return bookLimit;
	}

}
