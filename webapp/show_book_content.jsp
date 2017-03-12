<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<title>图书列表</title>
		<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/menu.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/global.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/modal.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" charset="utf-8" />
		<!--[if IE 6]>
			<link rel="stylesheet" href="css/ie6.css" type="text/css" media="screen" charset="utf-8" />
		<![endif]-->		
		<style type="text/css">
			
		</style>
		
		 <script type="text/javascript" charset="utf-8">
		</script>
		
	</head>
	<body>
		<div id="header">
			<div class="col w5 bottomlast">
				<a href="" class="logo">
					<img src="images/logo.png" alt="Logo" />
				</a>
			</div>
			<div class="col w5 last right bottomlast">
				<p class="last">
					<s:if test="null==#session.user">
					<a href="loginJsp">登录</a>
						|<a href="registerJsp">注册</a>
					</s:if>
					<s:else>
							欢迎：<a href="#"><s:property value="#session.user.username" /> </a>&nbsp;|&nbsp;
							<a href="user/userJsp">用户中心</a>&nbsp;|&nbsp;
							<s:if test="#session.user.group>1">
							<a href="admin/listUsers">管理中心</a>&nbsp;|&nbsp;</s:if>
						<a href="logout">注销</a>
	
					</s:else>
				</p>
			</div>
			<div class="clear"></div>
		</div>
		<div id="wrapper"  >
			<div id="minwidth">
				<div id="holder" >
					<div id="menu">
						<div id="left"></div>
						<div id="right"></div>
						<ul>
							<li>
								<a href='collect/collectBook?selectedBookId=<s:property value="book.bookID"/>&selectedBookTitle=<s:property value="book.bookTitle" />'><span>收藏</span></a>
							</li>
						</ul>
						<div class="clear"></div>
					</div>
					
					<div id="submenu">
						<div class="modules_left">
						</div>
						<div class="title" style="color:black;">
							<span id="orderBy" name="orderBy"><s:property value="orderBy"/></span>
							
						<div class="modules_right">
								<div class="box-163css">
								</div>
						</div>
							
						</div>
						<div class="modules_right">
						</div>
					</div>
					<div id="desc" >
						<div class="body" style="min-height: 400px;">
							<div class="col w8 last" style="width:100%">
								<div class="content">
								<s:if test="#parameters.showType[0] == 0">
									<img alt="" src='show/getJPEG.action?htmlContent=<s:property value="book.authors"/>'>
								</s:if>
								<s:elseif test="#parameters.showType[0] == 1">
									<img alt="" src='show/getPNG.action?htmlContent=<s:property value="book.authors"/>'>
									<!-- <img alt="" src='show/getPNG.action'> -->
								</s:elseif>
								<s:elseif test="#parameters.showType[0] == 2">
									<s:property value="htmlContent" escape="false" />
								</s:elseif>
							</div>
								<div class="clear"></div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="clear"></div>
						<div id="body_footer">
							<div id="bottom_left"><div id="bottom_right"></div></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="footer">
			<p class="last">Copyright 2014- sdudoc - Created by <a href="http://www.sdu.edu.cn">www.sdu.edu.cn</a></p>
		</div>		
	</body>
</html>