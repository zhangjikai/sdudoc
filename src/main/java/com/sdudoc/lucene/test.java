package com.sdudoc.lucene;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sdudoc.dao.impl.BookDaoImpl;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		
		LuceneIndexManager lim = new LuceneIndexManager();
		lim.indexInit();

		//初始化索引
		FileDAO f = new FileDAO();
		List list = f.getFile();
		lim.createALL(list);
	
		
		
		//更新索引
	//	lim.updateAll(list);
		
		
		//查询
		System.out.println("请如数要查询的文件名称：");
		Scanner s = new Scanner(System.in);
		String searchString = s.next();
		 List posts = lim.search(searchString);
		 System.out.println("-----------------------------------------");
		 for(int i=0;i<posts.size();i++){
			Post post = (Post)posts.get(i);
			System.out.println("书籍id:"+post.getBookID()); 
			System.out.println("书籍标题:"+post.getBookTitle()); 
			System.out.println("摘要:"+post.getSummary()); 
			System.out.println("体例:"+post.getBookStyle()); 
			System.out.println("作者:"+post.getAuthors()); 
			System.out.println("-----------------------------------------");
		 }
		
	}

}
