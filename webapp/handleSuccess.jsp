<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>操作成功</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css/common.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	#content{
		width:1000px;
		height:500px;
		margin:0 auto;
		margin-top:20px;
		font-size:18px;
		text-align: center;
	}
	</style>
</head>
</head>

<body>
	<div id="content">
	<s:if test='#parameters.handleType[0]=="activeuser"'>
		用户激活成功，点此<a href="loginJsp">去登陆</a>
	</s:if>
	<s:elseif test='#parameters.handleType[0]=="resetPw"'>
		密码重置成功，点此<a href="loginJsp">去登陆</a>
	</s:elseif>
	<s:elseif test='#parameters.handleType[0]=="send_active_email"'>
		注册成功，激活邮件已发送至您的邮箱，请注意查收
	</s:elseif>
	<s:elseif test='#parameters.handleType[0]=="resend_active_email"'>
		邮件已发送至您的邮箱，请注意查收
	</s:elseif>
	<s:elseif test='#parameters.handleType[0]=="sendPasswordEmail"'>
		密码重置邮件已发送到您的邮箱，请注意查收
	</s:elseif>
	<s:elseif test='#parameters.handleType[0]=="user_login"'>
		用户登录成功，<a href="index">点此</a>去首页
	</s:elseif>
	<s:elseif test='#parameters.handleType[0]=="updatePw"'>
		修改密码成功，<a href="index">点此</a>去首页
	</s:elseif>
	<s:elseif test='#parameters.handleType[0]=="deleteUser"'>
		删除用户成功，<a href="admin/listUsers">点此</a>返回
	</s:elseif>
	
	<s:elseif test='#parameters.handleType[0]=="collectBook"'>
		收藏书籍成功，<a href="collect/listCollects">点此</a>返回
	</s:elseif>
	
	<s:elseif test='#parameters.handleType[0]=="deleteCollect"'>
		删除书籍成功，<a href="collect/listCollects">点此</a>返回
	</s:elseif>
	</div>
</body>
</html>
