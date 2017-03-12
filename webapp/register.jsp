<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">

<title>新用户注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/common.css">

<script type="text/javascript">
	function changeValidateCode(obj) {
		//获取当前的时间作为参数，无具体意义  
		var timenow = new Date().getTime();
		//每次请求需要一个不同的参数，否则可能会返回同样的验证码  
		//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。  
		obj.src = "randomImage.action?timeNow=" + timenow;
	}
	window.onload = function() {
		 $("#username").blur(checkUserName);
		 $("#email").blur(checkEmail);
	}
</script>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>

<body>
	<div id="login" class="bor">
		<h1>新用户注册</h1>
		<a class="back" href="index">返回首页&gt;&gt;</a>
		<div class="theme">
			<s:form action="register" namespace="/" method="post" onsubmit="return checkRegister();">
		      <ul>
		        <li> 
		        	<span>用户名：</span>
		        	<s:textfield name="user.username" cssClass="intxt login_from" id="username" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" />
		        	<s:fielderror cssClass="error" ><s:param>user.username</s:param> </s:fielderror>
		        	<div class="error"><span id="uTip"></span></div>
		        </li>
		        <li> 
		        	<span>登录密码：</span>
		        	<s:password name="user.password" cssClass="intxt intxt w200" id="password"/>
		        	<s:fielderror cssClass="error" ><s:param>user.password</s:param> </s:fielderror>
		        </li>
		        <li> 
		        	<span>确认密码：</span>
		        	<s:password name="repassword" cssClass="intxt intxt w200" id="repassword"/>
		        	<s:fielderror cssClass="error" ><s:param>repassword</s:param> </s:fielderror>
		        </li>
		        <li> 
		        	<span>邮箱：</span>
		        	<s:textfield name="user.email" cssClass="intxt intxt w200" id="email"/>
		        	<s:fielderror cssClass="error" ><s:param>user.email</s:param> </s:fielderror>
		        	<div class="error"><span id="eTip"></span></div>
		        </li>
		        <li>
		        	<span>验证码：</span>
		        	<s:textfield cssClass="intxt w200" name="random" id="random" cssStyle="width:100px"/>&nbsp;&nbsp;&nbsp;
		        	<img src="randomImage.action" onclick="changeValidateCode(this)"  style="cursor: pointer;float: left;margin:7px 40px 0px 15px"/>
		        	<s:fielderror cssClass="error" ><s:param>random</s:param> </s:fielderror>
		         </li>
		        <li> <span>&nbsp;</span>
		        <s:submit value="确认注册" cssClass="login-btn" id="submitRegister"/>
		      </ul>
    		</s:form>
			<br class="clear" />
		</div>
	</div>
	<div class="footer"></div>
</body>
</html>
