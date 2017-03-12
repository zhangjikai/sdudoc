package com.sdudoc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdudoc.bean.Click;
import com.sdudoc.dao.ClickDao;
import com.sdudoc.service.ClickService;

@Service("clickService")
public class ClickServiceImpl implements ClickService{

	@Resource(name="clickDao")
	public ClickDao clickDao;
	
	@Override
	public void addClick(Click click) {
		clickDao.addClick(click);
	}
}
