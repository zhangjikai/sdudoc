package com.sdudoc.dao;

import java.util.List;

import com.sdudoc.bean.Click;

public interface ClickDao {

	public void addClick(Click click);
	
	public List<String> getRecommends(int userId);
}
