package com.sdudoc.lucene;


import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 * 主要构造分词器以及内存对象。
 * @author Administrator
 *
 */
public class LuceneIndexSettings {
	
	public Analyzer analyzer; //分析器
	public Directory directory; //目录位置

	/**
	 * 构造方法，初始化分析器。
	 * @param analyzer
	 */
	public LuceneIndexSettings(Analyzer analyzer){
		this.analyzer = analyzer;
	}
	
	/**
	 * 创建内存目录
	 */
	public void createRAMDirectory() throws Exception {
		this.directory = new RAMDirectory();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, this.analyzer);
		IndexWriter indexWriter = new IndexWriter(this.directory, indexWriterConfig);
		indexWriter.close();
	}
	/**
	 * 创建磁盘目录
	 */
	public void createFSDirectory(String path) throws Exception {
		this.directory =  FSDirectory.open(new File(path));
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, this.analyzer);
		IndexWriter indexWriter = new IndexWriter(this.directory, indexWriterConfig);
		indexWriter.close();
	}
	
	public Analyzer getAnalyzer() {
		return this.analyzer;
	}

	public Directory getDirectory() {
		return this.directory;
	}

}
