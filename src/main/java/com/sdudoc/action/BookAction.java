package com.sdudoc.action;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Controller;

import com.sdudoc.bean.Book;
import com.sdudoc.lucene.LuceneManager;
import com.sdudoc.lucene.Post;
import com.sdudoc.service.BookService;
import com.sdudoc.utils.Pager;


@Controller
@ParentPackage("sdudoc")
@Namespace("/book")
@Results({ @Result(name = BaseAction.DYNAMIC, location = "/${url}"),
		   @Result(name = BaseAction.SUCCESS, location = "/index.jsp") })
public class BookAction extends BaseAction{
	
	private static final long serialVersionUID = -7922737756224161234L;
	@Resource(name= "bookService")
	public BookService bookService;
	private Book book;
	private Pager<Book> page;
	private int pageNo=1;
	private int pageSize=12;
	private String searchString;//搜索书籍摘要的关键字
	private List posts;//搜索书籍得到的书籍列表
	private String dynasty;
	private String bookStyle;
	private List dynastyList;
	private List styleList;
	private String orderBy;
	private String bookTitle;
	private String author;
	private String searchType;
	
	@Action("showBookByDynasty")
	public String showBookByDynasty(){
		try {
			dynasty = new String(dynasty.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		orderBy=dynasty;
		searchType="dynasty";
		System.out.println("朝代---"+dynasty);
		page=bookService.showBookByDynasty(dynasty, pageNo, pageSize);
		url = "index.jsp";
		return DYNAMIC;
	}
	
	@Action("showBookByStyle")
	public String showBookByStyle(){
		try {
			bookStyle = new String(bookStyle.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		orderBy=bookStyle;
		searchType="bookStyle";
		page=bookService.showBookByStyle(bookStyle, pageNo, pageSize);
		url = "index.jsp";
		return DYNAMIC;
	}
	
	/*@Action("showBookByClickTimes")
	public String showBookByClickTimes(int pageNo,int pageSize){
		page=bookService.showBookByClickTimes(pageNo, pageSize);
		url = "index.jsp";
		return DYNAMIC;
	}*/
	
	@Action("showBookByClickTimes")
	public String showBookByTime() {
		searchType="clickTimes";
		page=bookService.showBookByClickTimes(pageNo, pageSize);
		url = "index.jsp";
		return DYNAMIC;
	}
	
	@Action("searchByTitle")
	public String searchByTitle(){
		try {
			bookTitle = new String(bookTitle.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行searchByTitle========="+bookTitle);
		page=bookService.searchByTitle(bookTitle, pageNo, pageSize);
		System.out.println(page.getRecords().size()+"---------------条数");
		url = "index.jsp";
		return DYNAMIC;
		
	}
	
	@Action("searchByAuthor")
	public String searchByAuthor() throws UnsupportedEncodingException{
		author = new String(author.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(author);
		page=bookService.searchByAuthor(author, pageNo, pageSize);
		url = "index.jsp";
		return DYNAMIC;
	}
	
	
	@Action("addBook")
	public void addBook(){
		bookService.addBook(book);
	}
	
	@Action("createIndex")
	public String createIndex(){
		LuceneManager lm=new LuceneManager();
		lm.createIndex();
		
		url = "";
		return null;
	}
	
	@Action("updateIndexAll")
	public String updateIndexAll(){
		LuceneManager lm=new LuceneManager();
		lm.updateIndexAll();
		url = "";
		return null;
	}
	
	@Action("searchSummary")
	public String searchSummary(){
		System.out.println(searchString+"-----------====");
		LuceneManager lm=new LuceneManager();
		posts= lm.searchSummary(searchString);
		for(int i=0;i<posts.size();i++){
			Post post = (Post)posts.get(i);
			System.out.println("书籍id:"+post.getBookID()); 
			System.out.println("书籍标题:"+post.getBookTitle()); 
			System.out.println("摘要:"+post.getSummary()); 
			System.out.println("体例:"+post.getBookStyle()); 
			System.out.println("作者:"+post.getAuthors()); 
			System.out.println("-----------------------------------------");
		 }
		url = "index2.jsp";
		return DYNAMIC;
	}
	
	@Action("checkDynasty")
	public String checkDynasty(){
		dynastyList=bookService.checkDynasty();
		JSONArray jsonArray = JSONArray.fromObject(dynastyList);
		//ajax返回客户端
		jsonArray.toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=UTF-8");
		try {
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@Action("checkStyle")
	public String checkStyle(){
		styleList=bookService.checkStyle();
		JSONArray jsonArray = JSONArray.fromObject(styleList);
		//ajax返回客户端
		jsonArray.toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=UTF-8");
		try {
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Pager<Book> getPage() {
		return page;
	}

	public void setPage(Pager<Book> page) {
		this.page = page;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getDynasty() {
		return dynasty;
	}

	public void setDynasty(String dynasty) {
		this.dynasty = dynasty;
	}

	public String getBookStyle() {
		return bookStyle;
	}

	public void setBookStyle(String bookStyle) {
		this.bookStyle = bookStyle;
	}

	public List getDynastyList() {
		return dynastyList;
	}

	public void setDynastyList(List dynastyList) {
		this.dynastyList = dynastyList;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public List getStyleList() {
		return styleList;
	}

	public void setStyleList(List styleList) {
		this.styleList = styleList;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List getPosts() {
		return posts;
	}

	public void setPosts(List posts) {
		this.posts = posts;
	}
	
	
}
