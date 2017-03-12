package com.sdudoc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdudoc.bean.User;
import com.sdudoc.dao.UserDao;
import com.sdudoc.service.UserService;
import com.sdudoc.utils.Pager;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDao")
	public UserDao userDao;

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public void deleteUser() {}

	@Override
	public boolean deleteUserById(int userId, int group) {
		return userDao.deleteUserById(userId, group);
	}
	
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}

	@Override
	public User login(String username, String password) {
		return userDao.getUserByName$Pw(username, password);
	}

	@Override
	public Pager<User> listUsers(int pageNo, int pageSize) {
		return userDao.listUserByPage(pageNo, pageSize);
	}

	
}
