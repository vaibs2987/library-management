package com.library.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.library.model.Book;
import com.library.model.User;

/**
 * 
 * Class responsible for loading some sample data.
 *
 */
@Service
public class DataService {
	@Autowired
	private FileReaderService fileReaderService;

	@Autowired
	private HelperService helperService;

	public Set<User> createUserObject() {
		String path = "users.csv";
		Set<User> users = new HashSet<>();
		List<String> list = fileReaderService.readFile(path);
		for (String string : list) {
			String[] array = string.split(",");
			if (!StringUtils.isEmpty(array) && array.length > 0) {
				User user = new User(array[0], array[1], null);
				user.setId(helperService.getRandomNumuber());
				users.add(user);
			}
		}
		return users;
	}

	public List<Book> createBookObject(String path) {
		List<Book> books = new ArrayList<>();
		List<String> list = fileReaderService.readFile(path);
		for (String string : list) {
			String[] array = string.split(",");
			if (!StringUtils.isEmpty(array) && array.length > 0) {
				Book book = new Book();
				book.setTitle(array[0]);
				book.setCategoryId(Long.parseLong(array[1]));
				book.setAuthorId(Long.parseLong(array[2]));
				book.setTotalQuanity(Integer.parseInt(array[3]));
				book.setCurrnetQuantity(Integer.parseInt(array[4]));
				books.add(book);
			}
		}
		return books;
	}
}
