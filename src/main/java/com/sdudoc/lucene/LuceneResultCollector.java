package com.sdudoc.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;


public class LuceneResultCollector {
	private LuceneIndexSettings luceneIndexSettings;  //lucene设置类
	
	/**
	 * 初始化方法
	 * @param luceneIndexSettings
	 */
	public LuceneResultCollector(LuceneIndexSettings luceneIndexSettings){
		this.luceneIndexSettings = luceneIndexSettings;
	}
	
	public List collect(ScoreDoc[] result,IndexSearcher indexSearcher) throws CorruptIndexException, IOException{
		List posts = new ArrayList();
		for(int i=0; i<result.length; i++){
			Post post = new Post();
			post.setBookID(indexSearcher.doc(result[i].doc).get("bookID"));
			System.out.println("结果返回======"+indexSearcher.doc(result[i].doc).get("bookID"));
			post.setBookTitle(indexSearcher.doc(result[i].doc).get("bookTitle"));
			post.setSummary(indexSearcher.doc(result[i].doc).get("summary"));
			post.setBookStyle(indexSearcher.doc(result[i].doc).get("bookStyle"));
			post.setAuthors(indexSearcher.doc(result[i].doc).get("authors"));
			posts.add(post);
		}
		return posts;
	}
}
