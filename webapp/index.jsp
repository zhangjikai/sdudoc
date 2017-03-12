<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
.cat {
	width: 130px;
	height: 35px;
	margin: 5px;
}

.cat span {
	width: 130px;
	text-align: center;
}

.shelf {
	height: 220px;
}

.shelf .inside {
	height: 175px;
}

.shelf .articles .show {
	width: 140px;
	height: 175px;
	float: left;
	text-align: left;
	margin: 0 auto;
	margin-left: 13px; font-size : 12px;
	font-family: "宋体", "Arail";
	background: url(images/document.png) no-repeat center bottom;
	font-size: 12px;
	/* border: 1px dotted; */
}

.shelf .articles .show .title {
	width: 130px;
	height: 30px;
	margin: 0 auto;
	line-height: 15px;
	font-weight: normal;
	overflow: hidden;
	padding: 5px;
	padding-bottom: 0px;
	/* border-bottom: 1px dotted; */
}

.shelf .articles .show .content {
	width: 130px;
	height: 100px;
	margin-top: 5px;
	line-height: 15px;
	padding: 5px;
	/* border-top: 1px dotted; 
				border-bottom: 1px dotted;  */
}

.shelf .articles .show .type {
	width: 130px;
	height: 20px;
}

.shelf .articles .show a {
	width: auto;
	height: auto;
	background: none;
}

.shelf .articles .show .type a {
	float: left;
	width: 40px;
	height: auto;
	text-align: center;
	color: black;
	background: none;
}

form select {
	width: 150px;
}
</style>

<script type="text/javascript" charset="utf-8">
	var selectListShow = 0;
	$(function() {
		$("#type,.searchselectbtn").click(function() {
			if (selectListShow) {
				$("#selectTypeList").slideUp("fast");
				selectListShow = 0;
			} else {
				$("#selectTypeList").slideDown("fast");
				selectListShow = 1;
			}
			return false;
		});
		$("body").click(function() {
			if (selectListShow) {
				$("#selectTypeList").slideUp("fast");
				selectListShow = 0;
			}
		});
		$(".searchselect li").click(function() {
			$("#type").text($(this).text());
			$("#type").attr("typename", $(this).attr("typename"));
			$(this).parent().hide();
		});

	});

	function search() {
		var type = $("#type").attr("typename");
		var key = document.getElementById("key").value;
		if (type == "summary") {
			window.location.href = "book/searchSummary?searchString=" + key;
		}
		if (type == "title") {
			window.location.href = "book/searchByTitle?bookTitle=" + key;
		}
		if (type == "author") {
			window.location.href = "book/searchByAuthor?author=" + key;
		}

		/* alert(key); */

	}

	//分页
	function firstPage(){
		var pageNo=1;
		var orderBy= $("#orderBy").html();
		var searchType= document.getElementById("searchType").value;
		if(searchType=="dynasty"){
			var str="book/showBookByDynasty?pageNo="+pageNo+"&dynasty="+orderBy;
			window.location.href=str;
		}
		if(searchType=="bookStyle"){
			var str="book/showBookByStyle?pageNo="+pageNo+"&bookStyle="+orderBy;
			window.location.href=str;
		}
		
		if(searchType == "clickTimes") {
			var str="book/showBookByClickTimes?pageNo="+pageNo;
			window.location.href=str;
		}
		
	}
	function prePage(){
		var pageNo=parseInt(document.getElementById("pageNo").value)-1;
		if(pageNo < 1) {
			pageNo = 1;
		}
		var orderBy= $("#orderBy").html();
		var searchType= document.getElementById("searchType").value;
		if(searchType=="dynasty"){
			var str="book/showBookByDynasty?pageNo="+pageNo+"&dynasty="+orderBy;
			window.location.href=str;
		}
		if(searchType=="bookStyle"){
			var str="book/showBookByStyle?pageNo="+pageNo+"&bookStyle="+orderBy;
			window.location.href=str;
		}
		
		if(searchType == "clickTimes") {
			var str="book/showBookByClickTimes?pageNo="+pageNo;
			window.location.href=str;
		}
	}
	function nextPage(){
		var pageNo=parseInt(document.getElementById("pageNo").value)+1;
		var orderBy= $("#orderBy").html();
		var searchType= document.getElementById("searchType").value;
		if(searchType=="dynasty"){
			var str="book/showBookByDynasty?pageNo="+pageNo+"&dynasty="+orderBy;
			window.location.href=str;
		}
		if(searchType=="bookStyle"){
			var str="book/showBookByStyle?pageNo="+pageNo+"&bookStyle="+orderBy;
			window.location.href=str;
		}
		if(searchType == "clickTimes") {
			var str="book/showBookByClickTimes?pageNo="+pageNo;
			window.location.href=str;
		}
	}
	
	function lastPage(){
		var pageNo=parseInt(document.getElementById("totalPage").value);
		var orderBy= $("#orderBy").html();
		var searchType= document.getElementById("searchType").value;
		if(searchType=="dynasty"){
			var str="book/showBookByDynasty?pageNo="+pageNo+"&dynasty="+orderBy;
			window.location.href=str;
		}
		if(searchType=="bookStyle"){
			var str="book/showBookByStyle?pageNo="+pageNo+"&bookStyle="+orderBy;
			window.location.href=str;
		}
		if(searchType == "clickTimes") {
			var str="book/showBookByClickTimes?pageNo="+pageNo;
			window.location.href=str;
		}
	}
	
	
	$(document).ready(
			
			
			function() {
				
				$("#firstPage").click(firstPage);
				$("#prePage").click(prePage);
				$("#nextPage").click(nextPage);
				$("#lastPage").click(lastPage);
				
				
				$("#searchbtn").click(search);
				
				$.ajax({
					type : "post",
					url : "book/checkDynasty",
					dataType : "json",
					success : function(result) {
						for ( var i = 0; i < result.length; i++) {
							var dynasty = result[i];
							$("#dynasty").append("<option value="+dynasty+">" + dynasty + "</option>");
						}
					}
				});

				$.ajax({
					type : "post",
					url : "book/checkStyle",
					dataType : "json",
					success : function(result) {
						for ( var i = 0; i < result.length; i++) {
							var bookStyle = result[i];
							$("#bookStyle").append(
									"<option value="+bookStyle+">" + bookStyle
											+ "</option>");
						}
					}
				});

				$("#dynasty").change(function() {
					var dynastyForm = $("#dynastyForm");
					dynastyForm.submit();

				});

				$("#bookStyleForm").change(function() {
					var bookStyleForm = $("#bookStyleForm");
					bookStyleForm.submit();

				});


			});
</script>


</head>
<body>
	<div id="header">
		<div class="col w5 bottomlast">
			<a href="" class="logo"> <img src="images/logo.png" alt="Logo" />
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
	<div id="wrapper">
		<div id="minwidth">
			<div id="holder">
				<div id="menu">
					<div id="left"></div>
					<div id="right"></div>
					<div class="clear"></div>
				</div>
				<div id="submenu">
					<div class="modules_left"></div>
					<div class="title" style="color:black;">
						<span id="orderBy" name="orderBy" ><s:property value="orderBy"/></span>
						<input type="hidden" id="searchType" value="${searchType}"/>
						<input type="hidden" id="pageNo" value="${pageNo}"/>
						<input type="hidden" id="totalPage" value="${page.pageTotal}"/>
						
						
						<div class="modules_right">
							<div class="box-163css">
								<div class=searchbg></div>
								<div class=searchContainer>
									<div class=searchselect>
										<SPAN id="type" typename="summary">摘要</SPAN> <A class=searchselectbtn href=""></A>
										<ul id=selectTypeList>
											<li typename="summary"><a>摘要</a></li>
											<li typename="title"><a>标题</a></li>
											<li typename="author"><a>作者</a></li>
											<li class=last></li>
										</ul>
									</div>
									<input id="key" type="" value=""><a class="searchbtn" id="searchbtn" href="javascript:void(0);"></a>
								</div>
							</div>
						</div>

					</div>
					<div class="modules_right"></div>
				</div>
				<div id="desc">
					<div class="body">
						<div class="col w2">
							<div class="content">
								<div class="box header">
									<div class="head">
										<div></div>
									</div>
									<h2>分类</h2>
									<div class="desc">
										<div class="cat">
											<form action="book/showBookByDynasty" id="dynastyForm">
												<select name="dynasty" id="dynasty">
													<option value="option1">朝代</option>
												</select>
												<br />
											</form>
										</div>

										<div class="cat">
											<form action="book/showBookByStyle" id="bookStyleForm">
												<select name="bookStyle" id="bookStyle">
													<option value="option1">体例</option>
												</select>
												<br />
											</form>
										</div>

									</div>
									<div class="bottom">
										<div></div>
									</div>
								</div>
							</div>
						</div>
						<div class="col w8 last">
							<div class="content">

								<s:iterator value="page.records" status="status">

									<s:if test="#status.index % 4 == 0">
										<div class="shelf">
											<div class="left"></div>
											<div class="right"></div>
											<div class="inside">
												<div class="books articles">


													<div class="col w2_5">
														<div class="show">
															<div class="title">
																<span>书号：<s:property value="page.records[#status.index].bookID"/></span><br/>
																<span>书名：<s:property value="page.records[#status.index].bookTitle" /></span>
															</div>
															<div class="content">
																<span>作者：<s:property value="page.records[#status.index].authors" /> <br /> 朝代：<s:property
																		value="page.records[#status.index].dynasty" /> <br /> 体例：<s:property value="page.records[#status.index].bookStyle" />
																</span>
															</div>
															<div class="type">
																<a href='show/showBookContent?showType=0&bookID=<s:property value="page.records[#status.index].bookID" />' title="以JPG方式演示">JPG</a> 
																<a href='show/showBookContent?showType=1&bookID=<s:property value="page.records[#status.index].bookID" />' title="以PNG方式演示">PNG</a> 
																<a href='show/showBookContent?showType=2&bookID=<s:property value="page.records[#status.index].bookID" />' title="以html方式演示">html</a>
															</div>
														</div>
													</div>

													<s:if test="#status.count+1<=page.records.size">
														<div class="col w2_5">
															<div class="show">
																<div class="title">
																<span>书号：<s:property value="page.records[#status.index+1].bookID"/></span><br/>
																	<span>作者：<s:property value="page.records[#status.index+1].bookTitle" /></span>
																</div>
																<div class="content">
																	<span>简介：<s:property value="page.records[#status.index+1].authors" /> <br /> 朝代：<s:property
																			value="page.records[#status.index+1].dynasty" /> <br /> 体例：<s:property value="page.records[#status.index+1].bookStyle" />
																	</span>
																</div>
																<div class="type">
																	<a href='show/showBookContent?showType=0&bookID=<s:property value="page.records[#status.index+1].bookID" />' title="以JPG方式演示">JPG</a>
																	<a href='show/showBookContent?showType=1&bookID=<s:property value="page.records[#status.index+1].bookID" />' title="以PNG方式演示">PNG</a>
																	<a href='show/showBookContent?showType=2&bookID=<s:property value="page.records[#status.index+1].bookID" />' title="以html方式演示">html</a>
																</div>
															</div>
														</div>
													</s:if>

													<s:if test="#status.count+2<=page.records.size">
														<div class="col w2_5">
															<div class="show">
																<div class="title">
																	<span>书号：<s:property value="page.records[#status.index+2].bookID"/></span><br/>
																	<span>书名：<s:property value="page.records[#status.index+2].bookTitle" /></span>
																</div>
																<div class="content">
																	<span>作者：<s:property value="page.records[#status.index+2].authors" /> <br /> 朝代：<s:property
																			value="page.records[#status.index+2].dynasty" /> <br /> 体例：<s:property value="page.records[#status.index+2].bookStyle" />
																	</span>
																</div>
																<div class="type">
																	<a href='show/showBookContent?showType=0&bookID=<s:property value="page.records[#status.index+2].bookID" />' title="以JPG方式演示">JPG</a>
																	<a href='show/showBookContent?showType=1&bookID=<s:property value="page.records[#status.index+2].bookID" />' title="以PNG方式演示">PNG</a>
																	<a href='show/showBookContent?showType=2&bookID=<s:property value="page.records[#status.index+2].bookID" />' title="以html方式演示">html</a>
																</div>
															</div>
														</div>
													</s:if>

													<s:if test="#status.count+3<=page.records.size">
														<div class="col w2_5">
															<div class="show">
																<div class="title">
																	<span>书号：<s:property value="page.records[#status.index+3].bookID"/></span><br/>
																	<span>书名：<s:property value="page.records[#status.index+3].bookTitle" /></span>
																</div>
																<div class="content">
																	<span>作者：<s:property value="page.records[#status.index+3].authors" /> <br /> 朝代：<s:property
																			value="page.records[#status.index+3].dynasty" /> <br /> 体例：<s:property value="page.records[#status.index+3].bookStyle" />
																	</span>
																</div>
																<div class="type">
																	<a href='show/showBookContent?showType=0&bookID=<s:property value="page.records[#status.index+3].bookID" />' title="以JPG方式演示">JPG</a>
																	<a href='show/showBookContent?showType=1&bookID=<s:property value="page.records[#status.index+3].bookID" />' title="以PNG方式演示">PNG</a>
																	<a href='show/showBookContent?showType=2&bookID=<s:property value="page.records[#status.index+3].bookID" />' title="以html方式演示">html</a>
																</div>
															</div>
														</div>
													</s:if>


													<%-- <div class="col w2_5">
													<a href="">
														<span><s:property value="page.records[#status.index+4].bookTitle"/></span>
														<small>
															<s:property value="page.records[#status.index+4].authors"/>
														</small>
													</a>
												</div> --%>

												</div>
											</div>
											<div class="clear"></div>

										</div>
									</s:if>
								</s:iterator>
								<div class="fenye" style="margin-left: auto;margin-right: auto;">
									<a href="javascript:void(0);" class="font_b">总共：<s:property value="page.pageTotal"/>&nbsp;页</a>&nbsp;&nbsp;
									<a href="javascript:void(0);" class="font_b">当前第：<s:property value="pageNo"/>&nbsp;页</a>&nbsp;|&nbsp;
									<a href="javascript:void(0);" id="firstPage" class="font_b">首页</a>
									<a href="javascript:void(0);" id="prePage" class="font_b">上一页</a>
									<a href="javascript:void(0);}" id="nextPage" class="font_b">下一页</a>
									<a href="javascript:void(0);}" id="lastPage" class="font_b">尾页</a>
								</div>

								<hr />
								<div>
									<span>猜你喜欢：</span>
								</div>
								<s:action name="recommendBook" namespace="/show">
								</s:action>
								<div class="shelf">
									<div class="left"></div>
									<div class="right"></div>
									<div class="inside">
										<div class="books articles">
											
											<s:iterator value="#request.recommeds" status="status">
												<s:if test="#status.index < 4">

													<div class="col w2_5">
														<div class="show">
															<div class="title">
																<span>书名：<s:property value="bookTitle" /></span>
															</div>
															<div class="content">
																<span>简介：<s:property value="authors" /> <br /> 朝代：<s:property
																		value="dynasty" /> <br /> 体例：<s:property value="bookStyle" />
																</span>
															</div>
															<div class="type">
																<a href='show/showBookContent?showType=0&bookID=<s:property value="bookID" />' title="以JPG方式演示">JPG</a> <a
																	href='show/showBookContent?showType=1&bookID=<s:property value="bookID" />' title="以PNG方式演示">PNG</a> <a
																	href='show/showBookContent?showType=2&bookID=<s:property value="bookID" />' title="以html方式演示">html</a>
															</div>
														</div>
													</div>

												</s:if>
											</s:iterator>

										</div>
									</div>
									<div class="clear"></div>

								</div>



							</div>
							<div class="clear"></div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
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
</body>
</html>