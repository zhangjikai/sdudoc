<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<title>用户中心</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" charset="utf-8" />
<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="js/global.js" type="text/javascript" charset="utf-8"></script>
<script src="js/modal.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div id="header">
		<div class="col w5 bottomlast">
			<a href="index" class="logo"> <img src="images/logo.png" alt="返回首页" title="返回首页" />
			</a>
		</div>
		<div class="col w5 last right bottomlast">

			<p class="last">
				欢迎您： <span class="strong"> <s:property value="#session.user.username" />&nbsp;
				</span> <a href="logout">退出</a>
			</p>
		</div>
		<div class="clear"></div>
	</div>
	<div id="wrapper">
		<div id="minwidth">
			<div id="holder">
				<div id="menu">
					<div id="left"></div>
					<div id="right"></div>
					<ul>
						<li><a href="user/userJsp"> <span>用户信息</span>
						</a></li>
						<li><a href="user/updatePwJsp"> <span>更改密码 </span>
						</a></li>
						<li><a href="user/userLog"> <span>近期操作</span>
						</a></li>
						<li><a href="collect/listCollects" class="selected"> <span>收藏信息</span>
						</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<div id="table" class="help" style="min-height: 480px;">
					<h1>收藏信息</h1>
					<div class="col w10 last">
						<div class="content">
							<table>
								<tr>
									<th width="10%">书籍id</th>
									<th width="30%">书籍名称</th>
									<th width="30%">收藏时间</th>
									<th width="30%">操作</th>
								</tr>
								<s:iterator value="collectPager.records" status="status">
									<tr>
										<td><s:property value="bookId" /></td>
										<td><s:property value="bookTitle" /></td>
										<td><s:date name="collectDate" /></td>
										<td><a href='collect/deleteCollect?selectedCollectId=<s:property value="id"/>&selectedBookTitle=<s:property value="bookTitle"/>'>删除</a></td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="4">共<s:property value="collectPager.recordTotal" />条记录 | 共<s:property value="collectPager.pageTotal" />页 当前第<s:property
											value="collectPager.pageNO" />页 <a href='collect/listCollects?collectPageNo=<s:property value="collectPager.firstPageNo"/>'>首页</a> <a
										href='collect/listCollects?collectPageNo=<s:property value="collectPager.prePageNo"/>'>上一页</a> <a
										href='collect/listCollects?collectPageNo=<s:property value="collectPager.nextPageNo"/>'>下一页</a> <a
										href='collect/listCollects?collectPageNo=<s:property value="collectPager.lastPageNo"/>'>尾页</a>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div id="body_footer">
					<div id="bottom_left">
						<div id="bottom_right"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div id="footer">
		<p class="last">
			Copyright 2014- sdudoc - Created by <a href="http://www.sdu.edu.cn">www.sdu.edu.cn</a>
		</p>
	</div>

	<!-- <embed type="application/x-shockwave-flash" width="600" height="400" src="http://lusongsong.com/images/lusongsongzhuamao.swf" wmode="transparent" quality="high" scale="noborder" flashvars="width=600&amp;height=400" allowscriptaccess="sameDomain" align="L"> -->
</body>
</html>