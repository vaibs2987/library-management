package com.library.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.model.Book;
import com.library.model.BookingHistory;
import com.library.util.HelperService;

@Repository
public class BookDaoImpl implements BookDao {

	private Map<Long, Book> availableBookMap = new LinkedHashMap<>();

	private Map<Long, Book> borrowedBookMap = new LinkedHashMap<>();

	@Autowired
	private HelperService helperService;

	@Override
	public Book addBook(Book book) {
		Long id = book.getId();
		if (id == null) {
			id = helperService.getRandomNumuber();
		}
		book.setId(id);
		book.setAddedDate(new Date());
		availableBookMap.put(id, book);
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> list = new ArrayList<>();
		list.addAll(availableBookMap.values());
		list.addAll(borrowedBookMap.values());
		return list;
	}

	@Override
	public Book getBookFromMap(Long bookId, boolean isAvailableMap) {
		Book book = null;
		if (isAvailableMap) {
			book = availableBookMap.get(bookId);
		} else {
			book = borrowedBookMap.get(bookId);
		}
		return book;
	}

	@Override
	public Book removeBookFromMap(Long bookId, boolean isAvailableMap) {
		Book book = availableBookMap.get(bookId);
		int currentQuantity = book.getCurrnetQuantity();
		if (isAvailableMap) {
			book.setCurrnetQuantity(currentQuantity++);

		} else {
			book.setCurrnetQuantity(currentQuantity--);
			book = borrowedBookMap.remove(bookId);
		}
		availableBookMap.put(bookId, book);
		return book;
	}

	@Override
	public Book addBookToBorrowedMap(BookingHistory bookingHistory) {
		Book book = getBookById(bookingHistory.getBookId());
		borrowedBookMap.put(book.getId(), book);
		return book;
	}

	@Override
	public Book getBookById(Long bookId) {
		System.out.println("Total: " + availableBookMap.size());
		return availableBookMap.get(bookId) == null ? borrowedBookMap.get(bookId) : availableBookMap.get(bookId);
	}

	@Override
	public void addBookToAvailableMap(Book book) {
		availableBookMap.put(book.getId(), book);
	}

}
