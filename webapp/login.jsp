<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>


<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
	function changeValidateCode(obj) {
		//获取当前的时间作为参数，无具体意义  
		var timenow = new Date().getTime();
		//每次请求需要一个不同的参数，否则可能会返回同样的验证码  
		//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。  
		obj.src = "randomImage.action?timeNow=" + timenow;
	}
</script>
<script type="text/javascript" src="js/login.js"></script>
<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="js/global.js" type="text/javascript" charset="utf-8"></script>
<script src="js/modal.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<!-- <a href="book/createIndex">初始化索引</a> -->

<div id="login" class="bor">
  <h1>用户登录</h1>
  <a class="back" href="index">返回首页&gt;&gt;</a>
  <div class="theme">
  	<s:form action="login" namespace="/" method="post" onsubmit="return checkLogin();">
      <ul>
        <li> 
        	<span>用户名：</span>
        	<s:textfield name="username" cssClass="intxt login_from" id="username" />
        	<s:fielderror cssClass="error" ><s:param>username</s:param> </s:fielderror>
        </li>
        <li> 
        	<span>密&nbsp;&nbsp;&nbsp;码：</span>
        	<s:password name="password" cssClass="intxt login_from2" id="password"/>
        	<s:fielderror cssClass="error" ><s:param>password</s:param> </s:fielderror>
        </li>
        <li><span>验证码：</span>
        	<s:textfield cssClass="intxt w200" name="random" id="random" cssStyle="width:100px"/>&nbsp;&nbsp;&nbsp;
        	<img src="randomImage.action" onclick="changeValidateCode(this)"  style="cursor: pointer;float: left;margin:7px 40px 0px 15px"/>
        	<s:fielderror cssClass="error" ><s:param>random</s:param> </s:fielderror>
         </li>
        <li> 
        	<span>自动登录：</span>
          	<s:checkbox name="autoLogin" cssClass="checkbox" />
<%--           	<s:checkbox name="autoLogin" cssStyle="margin-top:10px;margin-left:5px;"/> --%>
        </li>
        <li> <span>&nbsp;</span>
        <s:submit value="登录" cssClass="login-btn"/>
        <a class="regbt" href="registerJsp" rel="nofollow">注册账号</a></li>
        
      </ul>
      <a href="resetPwJsp"  class="back" style="top:330px;right:50px;">找回密码</a>
    </s:form>
  </div>
  <br class="clear"/>
</div>
<div class="footer">
</div>
</body>
</html>
