package com.library.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.model.Book;
import com.library.model.User;
import com.library.util.HelperService;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private HelperService helperService;

	@Autowired
	private BookDao bookDao;

	private Map<Long, User> userMap = new LinkedHashMap<>();
	private Map<Long, List<Book>> userBookMap = new LinkedHashMap<>();

	@Override
	public Long createUser(User user, Long roleId) {
		Long id = user.getId();
		if (id == null) {
			id = helperService.getRandomNumuber();
		}
		user.setId(id);
		userMap.put(id, user);
		return id;
	}

	@Override
	public void addBookToUserMap(Long bookId, Long userId) {
		Book book = bookDao.getBookById(bookId);
		List<Book> list = userBookMap.get(userId);
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(book);
		userBookMap.put(userId, list);

	}

	@Override
	public void removeBookFromUserMap(Long bookId, Long userId) {

		List<Book> list = userBookMap.get(userId);
		for (Book book : list) {
			if (book.getId().equals(bookId)) {
				// book.setCurrentStatus(BookingStatus.RELEASED.name());
				break;
			}
		}
		userBookMap.put(userId, list);
	}

	@Override
	public List<Book> getBooksById(Long userId) {
		return userBookMap.get(userId);
	}

	@Override
	public int getBookCountByUser(Long userId) {
		List<Book> books = userBookMap.get(userId);
		return books == null ? 0 : books.size();
	}

	@Override
	public User getUserById(Long userId) {
		return userMap.get(userId);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		users.addAll(userMap.values());
		return users;
	}

}
