package com.sdudoc.lucene;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;


/**
 *主要对index进行初始化，对索引进行更新、删除、添加方法。 
 * @author Administrator
 *
 */
public class LuceneIndexManager {
	
	private LuceneIndexSettings indexSettings;
	private LuceneIndex luceneIndex;

	private LuceneIndexSearch luceneIndexSearch;
	
	/*
	 * 初始化方法
	 */
	public void indexInit() throws Exception {
		Analyzer analyzer = new IKAnalyzer();
	//	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
		this.indexSettings = new LuceneIndexSettings(analyzer);
		this.indexSettings.createFSDirectory("f:\\file");
		this.luceneIndex = new LuceneIndex(this.indexSettings);
		this.luceneIndexSearch = new LuceneIndexSearch(indexSettings, new LuceneResultCollector(indexSettings));
	}
	
	/**
	 * 创建索引
	 * @param post
	 */
	public void create(Post post){
		this.luceneIndex.createIndex(post);
	}
	
	/**
	 * 批量创建索引
	 * @param post
	 */
	public void createALL(List posts){
		this.luceneIndex.createIndexALL(posts);
		System.out.println("创建索引成功!");
	}
	
	/**
	 *更新索引 
	 * @return
	 */
	public void update(Post post){
		this.luceneIndex.updateIndex(post);
	}
	
	public void updateAll(List list){
		this.luceneIndex.updateIndexAll(list);
	}
	
	/**
	 * 删除索引
	 * @return
	 */
	public void delete(Post post){
		this.luceneIndex.delIndex(post);
	}
	
	/**
	 * 判断索引是否存在
	 * @return
	 */
	public boolean exist(Post post){
		return this.luceneIndex.exist(post);
	}
	
	/**
	 * 搜索
	 * @param searchString
	 * @return
	 * @throws Exception
	 */
	public List search(String searchString) throws Exception {
		return this.luceneIndexSearch.serarchFile(searchString);
	}
	
	public LuceneIndex getLuceneIndex() {
		return luceneIndex;
	}

	public LuceneIndexSearch getLuceneIndexSearch() {
		return luceneIndexSearch;
	}
}
