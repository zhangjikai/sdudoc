package com.sdudoc.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book implements Serializable {
	private static final long serialVersionUID = -7615403983362149978L;
	/**书籍id */
	private int bookID;
	/** 书名*/
	private String bookTitle;
	/** 作者们*/
	private String authors;
	/** 著作方式（著，编，辑，译，合著，合编，合辑，合译，注释）*/
	private String bookPatterns;
	/** 类别（马列毛，哲学，社科，经济，军事，法律，教、科、文、体，艺术，自然科学，语言，文学，历史，地理，医药卫生，工程技术，农业科学，综合参考）*/
	private String bookClass;
	/**地位 */
	private String bookPosition;
	/** 体例（纪传体，国别体，编年体，纪实本末体）*/
	private String bookStyle;
	/** 版本（孤本，珍本，善本，禁书本，进呈本，底本，巾箱本，通行本，足本，节本，选本，配本，百衲本，从书本，单行本，邋遢本，赝本，秘本，禁毁本，绣像本，石印本，手抄本，补本，保留本）*/
	private String bookVersion;
	/** 年份*/
	private Date year;
	/**年代 */
	private String dynasty;
	/**相关书籍 */
	private String relatedBook;
	/** 总页数*/
	private int totalVolume;
	/**总章节 */
	private int totalChapter;
	/** 摘要*/
	private String summary;
	/**点击次数 */
	private int clickTimes;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookID", length = 11, nullable = false, unique = true)
	public int getBookID() {
		return bookID;
	}

	@Column(name = "bookTitle", length = 50, nullable = false)
	public String getBookTitle() {
		return bookTitle;
	}

	@Column(name = "authors", length = 50, nullable = false)
	public String getAuthors() {
		return authors;
	}

	@Column(name = "bookPatterns", length = 10)
	public String getBookPatterns() {
		return bookPatterns;
	}

	@Column(name = "bookClass", length = 50)
	public String getBookClass() {
		return bookClass;
	}

	@Column(name = "bookPosition", length = 10)
	public String getBookPosition() {
		return bookPosition;
	}

	@Column(name = "bookStyle", length = 20,nullable = false)
	public String getBookStyle() {
		return bookStyle;
	}

	@Column(name = "bookVersion", length = 10)
	public String getBookVersion() {
		return bookVersion;
	}

	@Column(name = "year")
	public Date getYear() {
		return year;
	}

	@Column(name = "dynasty", length = 10,nullable = false)
	public String getDynasty() {
		return dynasty;
	}

	@Column(name = "relatedBook", length = 100)
	public String getRelatedBook() {
		return relatedBook;
	}

	@Column(name = "totalVolume", length = 8)
	public int getTotalVolume() {
		return totalVolume;
	}

	@Column(name = "totalChapter", length = 8)
	public int getTotalChapter() {
		return totalChapter;
	}

	@Column(name = "summary")
	public String getSummary() {
		return summary;
	}

	@Column(name = "clickTimes", length = 11)
	public int getClickTimes() {
		return clickTimes;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public void setBookPatterns(String bookPatterns) {
		this.bookPatterns = bookPatterns;
	}

	public void setBookClass(String bookClass) {
		this.bookClass = bookClass;
	}

	public void setBookPosition(String bookPosition) {
		this.bookPosition = bookPosition;
	}

	public void setBookStyle(String bookStyle) {
		this.bookStyle = bookStyle;
	}

	public void setBookVersion(String bookVersion) {
		this.bookVersion = bookVersion;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public void setDynasty(String dynasty) {
		this.dynasty = dynasty;
	}

	public void setRelatedBook(String relatedBook) {
		this.relatedBook = relatedBook;
	}

	public void setTotalVolume(int totalVolume) {
		this.totalVolume = totalVolume;
	}

	public void setTotalChapter(int totalChapter) {
		this.totalChapter = totalChapter;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setClickTimes(int clickTimes) {
		this.clickTimes = clickTimes;
	}

	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", bookTitle=" + bookTitle + ", authors=" + authors + ", bookPatterns="
				+ bookPatterns + ", bookClass=" + bookClass + ", bookPosition=" + bookPosition + ", bookStyle="
				+ bookStyle + ", bookVersion=" + bookVersion + ", year=" + year + ", dynasty=" + dynasty
				+ ", relatedBook=" + relatedBook + ", totalVolume=" + totalVolume + ", totalChapter=" + totalChapter
				+ ", summary=" + summary + ", clickTimes=" + clickTimes + "]";
	}

}
