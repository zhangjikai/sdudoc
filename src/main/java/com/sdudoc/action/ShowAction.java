package com.sdudoc.action;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sdudoc.annotation.MethodDesc;
import com.sdudoc.bean.Book;
import com.sdudoc.bean.User;
import com.sdudoc.service.BookService;
import com.sdudoc.service.RecommendService;
import com.sdudoc.utils.Constants;

@Controller
@ParentPackage("sdudoc")
@Namespace("/show")
@Scope("prototype")
@Results({ @Result(name = BaseAction.DYNAMIC, location = "/${url}"),
		@Result(name = BaseAction.SUCCESS, location = "/showBook.jsp") })
public class ShowAction extends BaseAction {

	private static final long serialVersionUID = -7489291823269192621L;

	@Resource(name="bookService")
	private BookService bookService;
	
	@Resource(name="recommendService")
	private RecommendService recommendService;
	
	private ByteArrayInputStream inputStream;

	private int showType;
	
	private int bookID;
	
	private Book book;
	
	private String htmlContent;
	
	public String showCategory() {
		return null;
	}
	
	@Action("showBooksJsp")
	public String showBooksJsp(){
		url = "show_books.jsp";
		return DYNAMIC;
	}
	
	@MethodDesc(description="查看书籍", opType=Constants.OP_CLICK)
	@Action("showBookContent")
	public String showBookContent() {
		book = bookService.getBookById(bookID);
		if(book == null) {
			url = "handleFailure.jsp?handleType=showBookContent";
			return DYNAMIC;
		}
		int clickTimes = book.getClickTimes() + 1;
		bookService.updateClickTime(bookID, clickTimes);
		setLogMessage(book.getBookTitle());
		session.put("book", book);
		
		url = "show_book_content.jsp";
		if(showType == Constants.SHOW_HTML) {
			htmlContent = "<h3>" + book.getSummary() + "</h3>";
		}
		return DYNAMIC;
	}

	@Action(value = "getJPEG", results = { @Result(name = "success", type = "stream", params = { "contentType",
			"image/jpeg", "inputStream", "inputStream" }) })
	public String getJPEG() throws IOException {
		this.setInputStream(getJPEGImage("JPEG"));
		return SUCCESS;
	}

	@Action(value = "getPNG", results = { @Result(name = "success", type = "stream", params = { "contentType",
			"image/png", "inputStream", "inputStream" }) })
	public String getPNG() throws IOException {
		this.setInputStream(getJPEGImage("PNG"));
		return SUCCESS;
	}
	
	@Action("recommendBook")
	public String recommendBook(){
		User user = (User) session.get(Constants.USER);
		int userId = 0;
		if(user != null) {
			userId = user.getId();
		}
		List<Book> lists = recommendService.getRecommeds(userId);
		request.setAttribute("recommeds", lists);
		return null;
	}

	private ByteArrayInputStream getJPEGImage(String imageType) throws IOException {
		int width = 500;
		int height = 500;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		// g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		//g.drawString("这是一幅" + imageType + "图片", 200, 100);
		htmlContent = new String(htmlContent.getBytes("ISO-8859-1"), "utf-8");
		g.drawString("这是一幅" + imageType + "图片: " + htmlContent, 10, 100);
		g.dispose();
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
		ImageIO.write(image, imageType, imageOut);
		input = new ByteArrayInputStream(output.toByteArray());
		return input;
	}
	
	

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
