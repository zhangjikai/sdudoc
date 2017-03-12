package com.sdudoc.dao;

import com.sdudoc.bean.User;
import com.sdudoc.utils.Pager;

public interface UserDao {

	public void addUser(User user);
	
	public void updateUser(User user);
	
	/**
	 * 根据用户Id来删除用户，如果发起删除动作的用户权限小于或等于要删除的用户，则删除失败
	 * @param userId 用户Id
	 * @param group 发起删除动作的用户所对应的权限
	 * @return
	 */
	public boolean deleteUserById(int userId, int group);
	
	public User getUserByEmail(String email);
	
	public User getUserByName(String username);
	
	public User getUserByName$Pw(String username, String password);
	
	public Pager<User> listUserByPage(int pageNo, int pageSize);
	
	
}
