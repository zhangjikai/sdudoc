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

<title>操作失败</title>

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

<body>
	<div id="content"> 
		<s:if test='#parameters.handleType[0]=="activeuser"'>
			用户激活失败，请稍后重试
		</s:if>
		<s:elseif test='#parameters.handleType[0]=="resetPw"'>
			密码重置失败，请稍候重试
		</s:elseif>
		<s:elseif test='#parameters.handleType[0]=="user_noactived"'>
			用户尚未激活，请点击激活邮件中的地址进行用户激活，
			<a href='resendActiveEmail?user.username=<s:property value="username"/>'>点此</a> 重新发送激活邮件
		</s:elseif> 
		<s:elseif test='#parameters.handleType[0]=="resend_active_email"'>
			邮件发送失败，请稍后重试
		</s:elseif>
		<s:elseif test='#parameters.handleType[0]=="updatePw"'>
			修改密码失败，请稍后重试
		</s:elseif>
		<s:elseif test='#parameters.handleType[0]=="deleteUser"'>
			修改密码失败，请稍后重试<a href="admin/listUsers">点此</a>返回
		</s:elseif>
		<s:elseif test='#parameters.handleType[0]=="deleteCollect"'>
			删除书籍失败，<a href="collect/listCollects">点此</a>返回
		</s:elseif>
		<s:elseif test='#parameters.handleType[0]=="collectBook"'>
			您已收藏该书籍，<a href="collect/listCollects">点此</a>返回
		</s:elseif>
	</div>
</body>
</html>
