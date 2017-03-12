package com.sdudoc.service;

import java.util.List;

import com.sdudoc.bean.Book;

/**
 * 用于推荐书籍
 * @author zhangjk
 *
 */
public interface RecommendService {

	public List<Book> getRecommeds(int userId);
	
	public List<String> getCollect(int userId);
}
