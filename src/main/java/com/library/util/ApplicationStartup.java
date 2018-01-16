package com.library.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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
 * Class is responsible for loading the data as soon as the container loaded.
 *
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private SubscriptionDao subscriptionDao;

	@Autowired
	private UserSubscriptionDao userSubscriptionDao;

	@Autowired
	private DataService dataService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private BookDao bookDao;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
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
		Set<User> users = dataService.createUserObject();
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
		List<Book> books = dataService.createBookObject(path);
		for (Book book : books) {
			book.setPublicationYear(new Date());
			bookDao.addBook(book);
		}
	}
}
