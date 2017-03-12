package com.sdudoc.action;

import java.io.ByteArrayInputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sdudoc.utils.Constants;
import com.sdudoc.utils.RandomNumUtil;

/**
 * 产生验证码
 * @author zhangjk
 *
 */
@Controller
@Scope("prototype")
public class RandomAction extends BaseAction {

	private static final long serialVersionUID = -7193209177116825032L;
	private ByteArrayInputStream inputStream;

	@Action(value = "randomImage", results = { @Result(name = "success", type = "stream", params = { "contentType",
			"image/jpeg", "inputStream", "inputStream" }) })
	public String randomImage() throws Exception {
		RandomNumUtil ranUtil = RandomNumUtil.Instance();
		this.setInputStream(ranUtil.getImage());// 取得带有随机字符串的图片
		session.put(Constants.RANDOM, ranUtil.getString());// 取得随机字符串放入HttpSession
		return SUCCESS;
	}
	
	public void setTimeNow(long time){}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
}
