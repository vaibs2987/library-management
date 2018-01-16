package com.library.dao;

import java.util.List;

import com.library.model.Book;
import com.library.model.User;

public interface UserDao {

	public Long createUser(User user, Long roleId);

	void addBookToUserMap(Long bookId, Long userId);

	void removeBookFromUserMap(Long bookId, Long userId);

	List<Book> getBooksById(Long userId);

	int getBookCountByUser(Long userId);

	User getUserById(Long userId);
}
