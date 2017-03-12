package com.sdudoc.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Controller;

import com.sdudoc.annotation.MethodDesc;
import com.sdudoc.annotation.Permission;
import com.sdudoc.bean.Collect;
import com.sdudoc.bean.User;
import com.sdudoc.service.CollectService;
import com.sdudoc.utils.Constants;
import com.sdudoc.utils.Pager;

@Controller
@ParentPackage("sdudoc")
@Namespace("/collect")
@Results({ @Result(name = BaseAction.DYNAMIC, location = "/${url}"),
		@Result(name = BaseAction.SUCCESS, location = "/index.jsp") })
public class CollectAction extends BaseAction {

	private static final long serialVersionUID = -4942157354425948252L;

	@Resource(name = "collectService")
	private CollectService collectService;
	
	private int selectedCollectId;
	
	private int selectedBookId;
	
	private String selectedBookTitle;
	
	private int collectPageNo = 1;
	
	private int collectPageSize = 10;
	
	private Pager<Collect> collectPager;

	@MethodDesc(description="收藏书籍")
	@Permission(Constants.USER_COMMON)
	@Action("collectBook")
	public String collectBook() throws UnsupportedEncodingException {
		if(selectedBookId<= 0 || selectedBookTitle == null) {
			 url = "handleFailure.jsp?handleType=collectBook";
			 return DYNAMIC;
		}
		//使用a标签传中文参数时解决乱码
		selectedBookTitle = new String(selectedBookTitle.getBytes("ISO-8859-1"), "utf-8");
		//System.out.println("selectedBookTitle:" + selectedBookTitle);
		User userLogin = (User) session.get(Constants.USER);
		Collect collect = collectService.getCollectByBook$User(selectedBookId, userLogin.getId());
		if(collect != null) {
			setLogMessage(selectedBookTitle + " 失败 （已收藏）");
			url = "handleFailure.jsp?handleType=collectBook";
			return DYNAMIC;
		}
		
		collect = new Collect();
		collect.setUserId(userLogin.getId());
		collect.setUsername(userLogin.getUsername());
		collect.setBookId(selectedBookId);
		collect.setBookTitle(selectedBookTitle);
		collect.setCollectDate(new Date());
		collectService.addCollect(collect);
		setLogMessage(selectedBookTitle);
		url = "handleSuccess.jsp?handleType=collectBook";
		return DYNAMIC;
	}
	
	@Permission(Constants.USER_COMMON)
	@Action("listCollects")
	public String ListCollects() {
		if(collectPageNo <= 0)
			collectPageNo = 1;
		if(collectPageSize <= 0)
			collectPageSize = 10;
		User user = (User) session.get(Constants.USER);
		collectPager = collectService.listCollectsByUser(user.getId(), collectPageNo, collectPageSize);
		url="user_collects.jsp";
		return DYNAMIC;
	}
	
	@MethodDesc(description = "删除收藏")
	@Permission(Constants.USER_COMMON)
	@Action("deleteCollect")
	public String deleteCollect() throws UnsupportedEncodingException{
		User user= (User) session.get(Constants.USER);
		boolean flag = collectService.deleteCollect(selectedCollectId, user.getId());
		selectedBookTitle = new String(selectedBookTitle.getBytes("ISO-8859-1"), "utf-8");
		if(flag) {
			setLogMessage(selectedBookTitle + " 成功");
			url = "handleSuccess.jsp?handleType=deleteCollect";

		} else {
			setLogMessage(selectedBookTitle + " 失败");
			url = "handleFailure.jsp?handleType=deleteCollect";
		}
		return DYNAMIC;
	}

	public int getSelectedBookId() {
		return selectedBookId;
	}

	public void setSelectedBookId(int selectedBookId) {
		this.selectedBookId = selectedBookId;
	}

	public String getSelectedBookTitle() {
		return selectedBookTitle;
	}

	public void setSelectedBookTitle(String selectedBookTitle) {
		this.selectedBookTitle = selectedBookTitle;
	}

	public int getCollectPageNo() {
		return collectPageNo;
	}

	public void setCollectPageNo(int collectPageNo) {
		this.collectPageNo = collectPageNo;
	}

	public int getCollectPageSize() {
		return collectPageSize;
	}

	public void setCollectPageSize(int collectPageSize) {
		this.collectPageSize = collectPageSize;
	}

	public Pager<Collect> getCollectPager() {
		return collectPager;
	}

	public void setCollectPager(Pager<Collect> collectPager) {
		this.collectPager = collectPager;
	}

	public int getSelectedCollectId() {
		return selectedCollectId;
	}

	public void setSelectedCollectId(int selectedCollectId) {
		this.selectedCollectId = selectedCollectId;
	}
}
