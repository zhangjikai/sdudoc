package com.sdudoc.service;

import com.sdudoc.bean.Collect;
import com.sdudoc.utils.Pager;

public interface CollectService {

	public void addCollect(Collect collect);
	
	public boolean deleteCollect(int collectId, int userId);
	
	public Collect getCollectByBook$User(int bookId, int userId);
	public Pager<Collect> listCollectsByUser(int userId, int pageNo, int pageSize);
}
