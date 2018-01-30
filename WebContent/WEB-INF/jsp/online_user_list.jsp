<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../commom/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<!--导航条 begin-->
	<nav class="navbar navbar-inverse">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="student_list.html">教务系统</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li ><a href="${ctx}/student?method=findstudent"><span
						class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;学生管理
						<span class="sr-only">(current)</span></a></li>
				<li><a href="${ctx}/banji?method=findbanji"><span
						class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a></li>
				<li><a href="${ctx}/course?method=findcourse"><span
						class="glyphicon glyphicon-list" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a></li>
				<li><a href="${ctx}/teacher?method=findteacher"><span
						class="glyphicon glyphicon-tags" aria-hidden="true"></span>&nbsp;&nbsp;教师管理</a></li>
				<li><a href="${ctx}/manager?method=getManagerPage"><span
						class="glyphicon glyphicon-tags" aria-hidden="true"></span>&nbsp;&nbsp;教务管理</a></li>
				<li class="active"><a href="${ctx}/user?method=getOnLinePage"><span
						class="glyphicon glyphicon-tag" aria-hidden="true"></span>&nbsp;&nbsp;在线列表</a></li>
			</ul>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!--导航栏 begin  -->
	<nav class="navbar navbar-default">
	<div>
		<a href="${ctx}/login?method=logout"> <span>${user.name}</span> <span
			class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;&nbsp;退出
		</a>
	</div>
	<div class="container">
		<!--导航栏 end  -->
		<!-- 搜索表单end -->
		<table class="table table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>用户名</th>
					<th>密码</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${onLineUserList}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.name}</td>
						<td>${user.password}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	</nav>

</body>
</html>