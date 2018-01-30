<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../commom/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap-3.3.7-dist/css/bootstrap.css"/>
	<script type="text/javascript">
	function delteacher(id) {
		var isDel = confirm("您确认要删除么？");
		if(isDel) {
			location.href = "${ctx}/teacher?method=deleteteacher&id=" + id;  
		}
	}
	</script>
	<script type="text/javascript">
	$(function(){
		$("#gender option[value='${searchCondition.gender}']").prop("selected", true);
	})
	</script>
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
		        <li ><a href="${ctx}/student?method=findstudent"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;学生管理 <span class="sr-only">(current)</span></a></li>
		        <li><a href="${ctx}/banji?method=findbanji"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a></li>
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
					  <a href="${ctx}/teacher?method=findteacher" class="list-group-item active">教师列表</a>
					  <a href="${ctx}/teacher?method=getteacherAdd" class="list-group-item">教师添加</a>
					</div>
				</div>
				<!--左边部分 end-->
				<!--右边部分 Benin-->
			<div class="col-md-10">
			<!-- 搜索表单begin -->
					<form action="${ctx}/teacher?method=searchByCondition" method="post">
						姓名：<input type="text" name="name" value="${searchCondition.name}"/>
						年龄：<input type="text" name="age" value="${searchCondition.age}"/>
						性别：<select id="gender" name="gender">
							  	<option value="">不限</option>
							  	<option value="男">男</option>
							  	<option value="女">女</option>
							  </select>
						<input type="submit" value="搜索"/>
					</form>
					<!-- 搜索表单end -->
					<table class="table table-hover">
				      <thead>
				        <tr>
				          <th>工号</th>
				          <th>姓名</th>
				          <th>年龄</th>
				          <th>性别</th>
				          <th>科目</th>
				          <th>删除</th>
				          <th>修改</th>
				        </tr>
				      </thead>
				           <tbody>
				      	<c:forEach items="${list}" var="teacher">
					        <tr>
					          <td>${teacher.id}</td>
					          <td>${teacher.name}</td>
					          <td>${teacher.age}</td>
					          <td>${teacher.gender}</td>
					          <td>${teacher.subject}</td>
					          <td><a href="javascript:delteacher(${teacher.id})" class="btn btn-warning" >删除</a></td>
			                  <td><a href="${ctx}/teacher?method=toUpdate&id=${teacher.id}" class="btn btn-info">修改</td>
					        </tr>
				      	</c:forEach>
				      </tbody>
				</div>
				
				<!--右边部分 end-->
		</div>
		<!--内部内容 end-->
	</body>
</html>
