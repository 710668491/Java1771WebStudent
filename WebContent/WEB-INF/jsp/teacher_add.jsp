<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../commom/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap-3.3.7-dist/css/bootstrap.css"/>
	<body>
		<!--导航条 begin-->
		<nav class="navbar navbar-inverse">
		  <div class="container">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="${ctx}/student?method=findstudent">教务系统</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li><a href="${ctx}/student?method=findstudent"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;学生管理 <span class="sr-only">(current)</span></a></li>
		        <li><a href="${ctx }/banji?method=findbanji"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a></li>
		        <li><a href="${ctx}/course?method=findcourse"><span class="glyphicon glyphicon-list" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a></li>
		         <li class="active"><a href="${ctx}/teacher?method=findteacher"><span class="glyphicon glyphicon-tags" aria-hidden="true"></span>&nbsp;&nbsp;教师管理</a></li>
		        <li><a href="${ctx}/manager?method=getManagerPage"><span class="glyphicon glyphicon-tags" aria-hidden="true"></span>&nbsp;&nbsp;教务管理</a></li>
		        <li><a href="${ctx}/user?method=getOnLinePage"><span class="glyphicon glyphicon-sunglasses" aria-hidden="true"></span>&nbsp;&nbsp;在线列表</a></li>
		      </ul>
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="${ctx}/login?method=logout"><span>${user.name}&nbsp;&nbsp; <span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;&nbsp;退出</a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
		<!--导航条 end-->
		<!--内部内容 benin-->
		<div class="container">
			<div class="row">
				<!--左边部分 begin-->
				<div class="col-md-2">
					<div class="list-group">
					  <a href="${pageContext.request.contextPath}/teacher?method=findteacher" class="list-group-item ">教师列表</a>
					  <a href="${pageContext.request.contextPath}/teacher?method=getteacherAdd" class="list-group-item active">教师添加</a>
					</div>
				</div>
				<!--左边部分 end-->
				<!--右边部分 Benin-->
			<div class="col-md-10">
				
				      <form action="${pageContext.request.contextPath}/teacher?method=addteacher" method="post">
					  <div class="form-group">
					    <label for="exampleInputEmail1">姓名</label>
					    <input type="text" name="name" class="form-control" id="exampleInputEmail1" placeholder="name">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputPassword1">年龄</label>
					    <input type="text" name="age" class="form-control" id="exampleInputPassword1" placeholder="age">
					  </div>
					    <div class="form-group">
					    <label for="exampleInputPassword1">性别</label>
					    <input type="text" name="gender" class="form-control" id="exampleInputPassword1" placeholder="gender">
					  </div>
					    <div class="form-group">
					    <label for="exampleInputPassword1">科目</label>
					    <input type="text" name="subject" class="form-control" id="exampleInputPassword1" placeholder="subject">
					  </div>
					  <button type="submit" class="btn btn-primary">添加教师</button>
					</form>
				      
				</div>
				
				<!--右边部分 end-->
		</div>
		<!--内部内容 end-->
	</body>
</html>