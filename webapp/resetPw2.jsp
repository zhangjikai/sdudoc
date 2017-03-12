<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码重置</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function checkPassword() {
		var password = document.getElementById("password").value.replace(/(^\s*)|(\s*$)/g, "");
		if(password == null || password == "") {
			alert("新密码不能为空");
			return false;
		}
		var repassword = document.getElementById("repassword").value.replace(/(^\s*)|(\s*$)/g, "");
		if(repassword == null || repassword == "") {
			alert("重复密码不能为空");
			return false;
		}
		
		if(password != repassword) {
			alert("两次密码不一致");
			return false;
		}
		return true;
	}
</script>
</head>

<body>
<div id="login" class="bor">
  <h1>密码重置</h1>
  <div class="theme">
  	<s:form action="resetPassword" namespace="/" method="post" onsubmit="return checkPassword();">
  		<s:hidden name="user.username" />
		<s:hidden name="user.checkCode"/>
	    <ul>
	        <li> 
	        	<span>新密码：</span>
	        	<s:password name="user.password" cssClass="intxt login_from" id="password" />
	        	<s:fielderror cssClass="error" ><s:param>user.password</s:param> </s:fielderror>
	        </li>
	        <li> 
	        	<span>重复密码：</span>
	        	<s:password name="repassword" cssClass="intxt login_from" id="repassword" />
	        	<s:fielderror cssClass="error" ><s:param>user.password</s:param> </s:fielderror>
	        </li>
	        <li> <span>&nbsp;</span>
	        <s:submit value="确定" cssClass="login-btn"/>
	     </ul>
    </s:form>
  </div>
  <br class="clear"/>
</div>
</body>
</html>
