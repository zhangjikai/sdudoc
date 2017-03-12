package com.sdudoc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdudoc.bean.Collect;
import com.sdudoc.dao.CollectDao;
import com.sdudoc.service.CollectService;
import com.sdudoc.utils.Pager;

@Service("collectService")
public class CollectServiceImpl implements CollectService {

	@Resource(name = "collectDao")
	public CollectDao collectDao;

	@Override
	public void addCollect(Collect collect) {
		collectDao.addCollect(collect);
	}
	
	@Override
	public boolean deleteCollect(int collectId, int userId) {
		return collectDao.deleteCollect(collectId, userId);
	}
	

	@Override
	public Collect getCollectByBook$User(int bookId, int userId) {
		return collectDao.getCollectByBook$User(bookId, userId);
	}


	@Override
	public Pager<Collect> listCollectsByUser(int userId, int pageNo, int pageSize) {
		return collectDao.listCollectsByUser(userId, pageNo, pageSize);
	}

	

}
