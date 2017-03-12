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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<title>用户中心</title>
		<!-- <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" charset="utf-8" /> -->
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/global.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/modal.js" type="text/javascript" charset="utf-8"></script>
		<style type="text/css">
			.error{
				width: 140px;
				float: right;
				text-align: center;
				height:24px;
				line-height: 24px;
			}
			.error ul {
				margin: 0 auto;
			}
			.error li {
				list-style: none;
				line-height: 24px;
			}
			
			.pass_{
				margin: 10px;
			}
			
			.my_{
				margin-top: 0px;
				height: 170px;
				border-top: 1px dotted;
				border-bottom: 1px dotted
			}
		</style>
	</head>
	<body>
		<div id="header">
			<div class="col w5 bottomlast">
				<a href="index" class="logo">
					<img src="images/logo.png" alt="返回首页" title="返回首页"/>
				</a>
			</div>
			<div class="col w5 last right bottomlast">
				
				<p class="last">欢迎您：
					<span class="strong">
						<s:property value="#session.user.username" />&nbsp;
					</span> 
					<a href="logout">退出</a>
				</p>
			</div>
			<div class="clear"></div>
		</div>
		<div id="wrapper">
			<div id="minwidth">
				<div id="holder"  >
					<div id="menu">
						<div id="left"></div>
						<div id="right"></div>
						<ul>
							<li>
								<a href="user/userJsp" >
									<span>用户信息</span>
								</a>
							</li>
							<li>
								<a href="user/updatePwJsp" class="selected">
									<span>更改密码 </span>
								</a>
							</li>
							<li>
								<a href="user/userLog" >
									<span>近期操作</span>
								</a>
							</li>
							<li>
								<a href="collect/listCollects" >
									<span>收藏信息</span>
								</a>
							</li>
						</ul>
						<div class="clear"></div>
					</div>
					<div id="form" class="help" style="min-height: 480px;" >
						<h1>更改密码</h1>
						<div class="col w5">
							<div class="content">
								<div class="my_">
								<s:form action="updatePw" namespace="/user" method="post" id="form">
									<div class="pass_">
										<label for="simple_input" >　旧密码:</label>
										<s:password name="oldPassword" cssClass="text w_20"/>
										<s:fielderror cssClass="error"><s:param>oldPassword</s:param> </s:fielderror>
									</div>
									<div  class="pass_">
										<label for="simple_input">　新密码:</label>
										<s:password name="newPassword" cssClass="text w_20"/>
										<s:fielderror cssClass="error"><s:param>newPassword</s:param> </s:fielderror>
									</div>
									<div  class="pass_">
										<label for="simple_input">重复密码:</label>
										<s:password name="newPassword2" cssClass="text w_20"/>
										<s:fielderror cssClass="error"><s:param>newPassword2</s:param> </s:fielderror>
									</div>
									<a href="javascript:void(document.form.submit())" class="button" style="float:right;margin-right: 172px;margin-top:2px;">
										<span >　确定　</span>
									</a>
								</s:form>
								</div>
							</div>
						</div>
						<div class="clear"></div>
					</div>
					<div id="body_footer">
						<div id="bottom_left">
							<div id="bottom_right"></div>
						</div>
					</div></div>
				</div>
			</div>
		</div>
		<div id="footer">
			<p class="last">Copyright 2014- sdudoc - Created by <a href="http://www.sdu.edu.cn">www.sdu.edu.cn</a></p>
		</div>
		
		
	</body>
</html>