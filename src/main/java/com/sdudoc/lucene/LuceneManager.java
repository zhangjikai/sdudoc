package com.sdudoc.lucene;

import java.util.List;

public class LuceneManager {

	/**
	 * 初始化索引
	 */
	public void createIndex(){
		LuceneIndexManager lim = new LuceneIndexManager();
		try {
			lim.indexInit();
			FileDAO f = new FileDAO();
			List list = f.getFile();
			lim.createALL(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 更新所有索引
	 */
	public void updateIndexAll(){
		LuceneIndexManager lim = new LuceneIndexManager();
		try {
			lim.indexInit();
			FileDAO f = new FileDAO();
			List list = f.getFile();
			lim.updateAll(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询摘要
	 * @param searchString
	 * @return
	 */
	public List searchSummary(String searchString){
		 LuceneIndexManager lim = new LuceneIndexManager();
		 List posts=null;
		 try {
			lim.indexInit();
			posts = lim.search(searchString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return posts;
	}
	
}
