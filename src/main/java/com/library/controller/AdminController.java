package com.library.controller;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dao.UserDao;
import com.library.model.User;

@RestController
@RequestMapping({ "/api/library/admin/v1" })
public class AdminController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = { "" }, method = { RequestMethod.GET })
	private User getUser(@RequestParam @NotNull Long id) {
		return userDao.getUserById(id);
	}

	@RequestMapping(value = { "/all" }, method = { RequestMethod.GET })
	private Collection<User> getUser() {
		return userDao.getAllUsers();
	}
}
