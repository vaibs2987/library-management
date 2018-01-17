package com.library.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.library.dao.BookDao;
import com.library.dao.RoleDao;
import com.library.dao.SubscriptionDao;
import com.library.dao.UserDao;
import com.library.dao.UserSubscriptionDao;
import com.library.model.Book;
import com.library.model.Role;
import com.library.model.Subscription;
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
	private UserSubscriptionDao userSubscriptionDao;

	@Autowired
	private HelperService helperService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private SubscriptionDao subscriptionDao;

	public void loadData() {
		BigDecimal charges = new BigDecimal(20.0);
		Subscription subscription = new Subscription(null, "MONTHLY", charges, 1, 4);
		Long monthlyId = subscriptionDao.createSubscription(subscription);
		System.out.println(monthlyId);
		charges = new BigDecimal(120.0);
		subscription = new Subscription(null, "YEARLY", charges, 12, 8);
		Long yearlyId = subscriptionDao.createSubscription(subscription);
		createUsers(monthlyId, yearlyId);
		inserBooks();
	}

	private void createUsers(Long monthlySubId, Long yearlySubId) {
		Set<User> users = createUserObject();
		Long adminId = roleDao.createRole("ADMIN");
		Role adminRole = roleDao.getRoleById(adminId);
		Long userId = roleDao.createRole("USER");
		Role userRole = roleDao.getRoleById(userId);
		int i = 1;
		for (User user : users) {
			Set<Role> roles = new HashSet<>();
			if (i == 1) {
				roles.add(adminRole);
				user.setUserRoles(roles);
			} else if (i % 2 == 0) {
				Long userSubscriptionDetailId = userSubscriptionDao.buySubscription(yearlySubId, user.getId());
				user.setCurrentSubscriptionDetailId(userSubscriptionDetailId);
				roles.add(userRole);
				user.setUserRoles(roles);

			} else {
				roles.add(userRole);
				user.setUserRoles(roles);
				Long userSubscriptionDetailId = userSubscriptionDao.buySubscription(yearlySubId, user.getId());
				user.setCurrentSubscriptionDetailId(userSubscriptionDetailId);

			}
			Long id = userDao.createUser(user, null);
			System.out.println("User :" + id);
			i++;
		}
	}

	private void inserBooks() {
		String path = "book.csv";
		List<Book> books = createBookObject(path);
		for (Book book : books) {
			book.setPublicationYear(new Date());
			bookDao.addBook(book);
		}
	}

	private Set<User> createUserObject() {
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

	private List<Book> createBookObject(String path) {
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
