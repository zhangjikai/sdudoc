package com.sdudoc.lucene;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.util.Version;

@SuppressWarnings("rawtypes")
public class LuceneIndexSearch {
	private IndexSearcher indexSearcher;  //indexSearcher对象
	private LuceneIndexSettings indexSettings;
	private LuceneResultCollector contentCollector;
	
	/**
	 * 构造方法对：indexSearcher、indexSettings、contentCollector进行初始化
	 * @param indexSettings
	 * @param contentCollector
	 */
	public LuceneIndexSearch(LuceneIndexSettings indexSettings,LuceneResultCollector contentCollector){
		this.indexSettings = indexSettings;
		this.contentCollector = contentCollector;
		//调用初始化indexSearch对象的方法
		createIndexSearch();
	}
	
	
	/**
	 * 初始化indexSearch对象的方法
	 * @throws Exception
	 */
	public void createIndexSearch(){
		try{
			IndexReader indexReader = DirectoryReader.open(this.indexSettings.directory);
			this.indexSearcher = new IndexSearcher(indexReader);
			//输出现在的索引
//	        for(int i =0; i<indexReader.numDocs();i++){
//	        	System.out.println(indexReader.document(i));
//	        	System.out.println("文件名称："+indexReader.document(i).get("fileName")+"\t文件描述:"+indexReader.document(i).get("fileDesc")+"\t文件ID："+indexReader.document(i).get("fileId")+"\t创建者："+indexReader.document(i).get("fileCreator"));
//	        }
//	        System.out.println("索引版本:" + indexReader.getCoreCacheKey());
//	    	System.out.println("索引内文档数量："+indexReader.numDocs());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询方法
	 * @throws IOException 
	 * @throws CorruptIndexException 
	 * @throws ParseException 
	 */
	public List Search(String searchString,LuceneResultCollector luceneResultCollector) throws CorruptIndexException, IOException, ParseException{
		//方法一:
		
		System.out.println(this.indexSettings.getAnalyzer().getClass()+"----分词选择");
		QueryParser q = new QueryParser(Version.LUCENE_44, "summary", this.indexSettings.getAnalyzer());
		String search = new String(searchString.getBytes("ISO-8859-1"),"UTF-8"); 
		System.out.println(search+"----------搜索的词语dd");
		Query query = q.parse(search);
		//方法二:
		/*
		Term t = new Term("title", searchString);
		TermQuery query = new TermQuery(t);
		*/
		System.out.println(query.toString()+"--------query.tostring");
		ScoreDoc[] docs = this.indexSearcher.search(query,100).scoreDocs;
		System.out.println("一共有:"+docs.length+"条记录");
		List result = luceneResultCollector.collect(docs, this.indexSearcher);
		return result;
	}
	
	/**
	 * 调用查询方法
	 * @param searchString
	 * @return
	 * @throws Exception
	 */
	public List serarchFile(String searchString) throws Exception{
		return this.Search(searchString, this.contentCollector);
	}
}
