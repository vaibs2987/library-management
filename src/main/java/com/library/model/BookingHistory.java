package com.library.model;

import java.util.Date;

public class BookingHistory {

	private Long id;

	private Long bookId;

	private Long userId;

	private Date bookedDate;

	private Date releaseDate;

	private Date actualReleaseDate;

	private String status;
	
	private Book book;

	public BookingHistory(Long bookId, Long userId, String status, Date bookedDate, Date releaseDate,
			Date actualReleaseDate) {
		this.bookId = bookId;
		this.userId = userId;
		this.status = status;
		this.bookedDate = bookedDate;
		this.releaseDate = releaseDate;
		this.actualReleaseDate = actualReleaseDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getActualReleaseDate() {
		return actualReleaseDate;
	}

	public void setActualReleaseDate(Date actualReleaseDate) {
		this.actualReleaseDate = actualReleaseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
