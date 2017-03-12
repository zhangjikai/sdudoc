<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码重置</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function checkUsername() {
	var username = document.getElementById("username").value.replace(/(^\s*)|(\s*$)/g, "");
	if(username == null || username == "") {
		alert("用户名不能为空");
		return false;
	}
	return true;
}

</script>
</head>

<body >
	<div id="login" class="bor">
  <h1>找回密码</h1>
  <div class="theme">
  	<s:form action="sendPassWordEmail" namespace="/" method="post" onsubmit="return checkUsername();">
      <ul>
        <li> 
        	<span>用户名：</span>
        	<s:textfield name="user.username" cssClass="intxt login_from" id="username" />
        	<s:fielderror cssClass="error" ><s:param>user.username</s:param> </s:fielderror>
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
