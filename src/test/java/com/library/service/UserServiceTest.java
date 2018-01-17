package com.library.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.config.PageWrapper;
import com.library.model.Book;
import com.library.model.BookingHistory;
import com.library.model.User;
import com.library.util.DataService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private LibraryService libraryService;

	@Autowired
	private DataService dataService;

	private User user = null;

	private Book book = null;

	@Before
	public void loadData() {
		dataService.loadData();
		this.user = getUser();
		this.book = getBook();

	}

	@Test
	public void firsttestBarrowBook() {
		Book borrowedBook = userService.barrowBook(book.getId(), user.getId());
		assertTrue(borrowedBook != null);
	}

	@Test
	public void secondtestGetAllMyBorrowedBook() {
		PageWrapper<Book> pages = userService.getAllMyBorrowedBook(user.getId(), 0, 2);
		assertTrue(pages != null);
	}

	@Test
	public void thirdTestGetAllBookHistoryByUserId() {
		PageWrapper<BookingHistory> pages = userService.getAllBookHistoryByUserId(user.getId(), 0, 2);
		assertTrue(pages != null);
	}

	@Test
	public void ztestReturnBook() {
		boolean status = userService.returnBook(book.getId(), user.getId());
		assertTrue(status);
	}

	private User getUser() {
		return libraryService.getAllUsers(0, 2).getItems().get(1);
	}

	private Book getBook() {
		return libraryService.getAllBooks(0, 2).getItems().get(0);
	}
}
