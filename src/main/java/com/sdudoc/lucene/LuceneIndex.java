package com.sdudoc.lucene;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;



/**
 * 索引创建、删除、更新、添加
 * @author Administrator
 *
 */
public class LuceneIndex {
	private static final Object MUTEX = new Object();
	private LuceneIndexSettings indexSettings;
	private Directory ramDirectory;
	
	
	/**
	 * 构造方法
	 */
	public LuceneIndex(LuceneIndexSettings luceneIndexSettings){
		this.indexSettings = luceneIndexSettings;
	}
	
	/**
	 * 刷新内存索引
	 */
	public void flushRAMDirectory(){
		synchronized (MUTEX) {
			IndexWriterConfig indexWriterConfig = null;
			IndexWriter indexWriter = null;
			ramDirectory = new RAMDirectory();
			try{
				indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, this.indexSettings.getAnalyzer());
				indexWriter = new IndexWriter(this.indexSettings.getDirectory(), indexWriterConfig);
				indexWriter.addIndexes(new Directory[]{this.ramDirectory});
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					indexWriter.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
	}
	
	/**
	 * 创建索引
	 * @param post
	 */
	public void createIndex(Post post){
			synchronized (MUTEX) {
				System.out.println("createIndex");
				IndexWriterConfig indexWriterConfig = null;
				IndexWriter indexWriter = null;
				try{
					indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, this.indexSettings.getAnalyzer());
					indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
					indexWriter = new IndexWriter(this.indexSettings.getDirectory(), indexWriterConfig);
					//调用创建document的方法
					 Document doc = createDocument(post);
					 System.out.println("我创建的doc:"+doc);
					 indexWriter.addDocument(doc);
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try{
						indexWriter.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
	}
	
	
	
	/**
	 * 批量创建索引
	 * @param list
	 */
	public void createIndexALL(List list){
			synchronized (MUTEX) {
				System.out.println("createIndexALL");
				IndexWriterConfig indexWriterConfig = null;
				IndexWriter indexWriter = null;
				try{
					indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, this.indexSettings.getAnalyzer());
					indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
					indexWriter = new IndexWriter(this.indexSettings.getDirectory(), indexWriterConfig);
					//调用创建document的方法
					 List docs = createDocumentAll(list);
					 System.out.println("一共有:"+docs.size());
					 for(int i=0; i<docs.size(); i++){
						 indexWriter.addDocument((Document)docs.get(i));
					 }
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try{
						indexWriter.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
	}
	
	
	/**
	 * 更新索引：先删除对应的索引，再更新。
	 * @param post
	 */
	public void updateIndex(Post post){
		if(delIndex(post)){
			createIndex(post);
		}
	}
	
	/**
	 * 更新全部索引
	 * @param list
	 */
	public void updateIndexAll(List list){
		synchronized (MUTEX) {
			System.out.println("createIndexALL");
			IndexWriterConfig indexWriterConfig = null;
			IndexWriter indexWriter = null;
			try{
				indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, this.indexSettings.getAnalyzer());
				indexWriterConfig.setOpenMode(OpenMode.CREATE);
				indexWriter = new IndexWriter(this.indexSettings.getDirectory(), indexWriterConfig);
				//调用创建document的方法
				 List docs = createDocumentAll(list);
				 System.out.println("一共有:"+docs.size());
				 for(int i=0; i<docs.size(); i++){
					 indexWriter.addDocument((Document)docs.get(i));
				 }
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					indexWriter.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 删除索引
	 * @param post 实体对象
	 * @return isOK删除结果
	 */
	public boolean delIndex(Post post){
		boolean isOK = false;
		synchronized (MUTEX) {
			IndexWriter indexWriter = null;
			IndexWriterConfig indexWriterConfig = null;
			try{
				indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, this.indexSettings.getAnalyzer());
				indexWriter = new IndexWriter(this.indexSettings.getDirectory(), indexWriterConfig);
				indexWriter.deleteDocuments(new Term("bookID",post.getBookID()));
				isOK = true;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					indexWriter.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return isOK;
	}
	/**
	 * 批量删除索引
	 * @param list
	 * @return
	 */
	public boolean delIndexAll(List<Post> list){
		synchronized (MUTEX) {
			IndexWriterConfig indexWriterConfig = null;
			IndexWriter indexWriter = null;
			try{
				indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, this.indexSettings.getAnalyzer());
				//indexWriterConfig.setOpenMode(OpenMode.CREATE);
				indexWriter = new IndexWriter(this.indexSettings.getDirectory(), indexWriterConfig);
				 System.out.println("一共有:"+list.size());
				
				 for(int i=0; i<list.size(); i++){
					 indexWriter.deleteDocuments(new Term("bookID",list.get(i).getBookID()));
				 }
				 
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					indexWriter.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	
	/**
	 * 创建document对象
	 * @param post 实体类
	 * @return document document对象
	 */
	public Document createDocument(Post post){
		Document document = new Document();
		document.add(new StringField("bookID",post.getBookID(),Store.YES));
		document.add(new TextField("bookTitle",post.getBookTitle(),Store.YES));
		document.add(new TextField("summary",post.getSummary(),Store.YES));
		document.add(new StringField("bookStyle",post.getBookStyle(),Store.YES));
		document.add(new StringField("authors",post.getAuthors(),Store.YES));
		return document;
	}
	
	/**
	 * 批量创创建document
	 * @param list
	 * @return
	 */
	public List  createDocumentAll(List list){
		List documents = new ArrayList();
		for(int i=0; i<list.size(); i++){
			Post post = (Post)list.get(i);
			Document document = new Document();
			document.add(new StringField("bookID",post.getBookID(),Store.YES));
			document.add(new TextField("bookTitle",post.getBookTitle(),Store.YES));
			document.add(new TextField("summary",post.getSummary(),Store.YES));
			document.add(new StringField("bookStyle",post.getBookStyle(),Store.YES));
			document.add(new StringField("authors",post.getAuthors(),Store.YES));
			documents.add(document);
		}
		return documents;
	}
	
	/**
	 * 判断索引是否存在
	 * @param post
	 * @return
	 */
	public boolean exist(Post post){
		boolean isExits = false;
		IndexReader indexReader = null;
		try{
			indexReader = DirectoryReader.open(this.indexSettings.getDirectory());
			int count = indexReader.docFreq(new Term("bookID",post.getBookID()));
			isExits = count > 0;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				indexReader.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return isExits;
	}
	
}
