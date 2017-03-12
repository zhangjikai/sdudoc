<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>书籍展示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  		<h2>以下是收藏</h2>
    	<a href="collect/collectBook?selectedBookId=1&selectedBookTitle=书籍1">书籍1</a> | 
    	<a href="collect/collectBook?selectedBookId=2&selectedBookTitle=书籍2">书籍2</a> |
    	<a href="collect/collectBook?selectedBookId=3&selectedBookTitle=书籍3">书籍3</a> |
    	<a href="collect/collectBook?selectedBookId=4&selectedBookTitle=书籍4">书籍4</a> |
    	<a href="collect/collectBook?selectedBookId=5&selectedBookTitle=书籍5">书籍5</a> |
    	<a href="collect/collectBook?selectedBookId=6&selectedBookTitle=书籍6">书籍6</a> |
    	<a href="collect/collectBook?selectedBookId=7&selectedBookTitle=书籍7">书籍7</a> |
    	<a href="collect/collectBook?selectedBookId=8&selectedBookTitle=书籍8">书籍8</a> |
    	<a href="collect/collectBook?selectedBookId=9&selectedBookTitle=书籍9">书籍9</a> 
    	<br />
    	<h2>---------------------------------------</h2>
    	<h2>以下是查看</h2>
    	<a href="show/showBookContent?showType=0&bookID=1">书籍1</a> 
  </body>
</html>
