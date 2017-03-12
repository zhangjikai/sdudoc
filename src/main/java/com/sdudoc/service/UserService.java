package com.sdudoc.service;

import com.sdudoc.bean.User;
import com.sdudoc.utils.Pager;

public interface UserService {

	public void addUser(User user);
	
	public void deleteUser();
	
	public boolean deleteUserById(int userId, int group);
	
	public void updateUser(User user);
	
	public User getUserByEmail(String email);
	
	public User getUserByName(String username);
	
	public User login(String username, String password);
	
	public Pager<User> listUsers(int pageNo, int pageSize);

}
