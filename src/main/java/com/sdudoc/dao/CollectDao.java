package com.sdudoc.dao;

import java.util.List;

import com.sdudoc.bean.Collect;
import com.sdudoc.utils.Pager;

public interface CollectDao {

	public void addCollect(Collect collect);
	
	public boolean deleteCollect(int collectId, int userId);
	
	public Collect getCollectByBook$User(int bookId, int userId);
	
	public Pager<Collect> listCollectsByUser(int userId, int pageNo, int pageSize);
	
	public List<String> getRecommend(int userId);
	
}
