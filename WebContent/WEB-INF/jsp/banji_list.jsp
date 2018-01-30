<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../commom/base.jsp" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap-3.3.7-dist/css/bootstrap.css"/>
		<script type="text/javascript">
	function delbanji(id) {
		var isDel = confirm("您确认要删除么？");
		if(isDel) {
			location.href = "${ctx}/banji?method=delete&id=" + id;  
		}
	}
	function goPage(pageNo){
		$("#pageNo").val(pageNo);
		$("#searchForm").submit();
	}
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
		        <li class="active"><a href="${ctx}/banji?method=findbanji"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a></li>
		        <li><a href="${ctx}/course?method=findcourse"><span class="glyphicon glyphicon-list" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a></li>
		        <li><a href="${ctx}/teacher?method=findteacher"><span class="glyphicon glyphicon-tags" aria-hidden="true"></span>&nbsp;&nbsp;教师管理</a></li>
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
					  <a href="${ctx}/banji?method=findbanji" class="list-group-item active ">班级列表</a>
					  <a href="${ctx}/banji?method=getbanjiAdd" class="list-group-item ">班级添加</a>
					</div>
				</div>
				<!--左边部分 end-->
				<!--右边部分 Benin-->
			<div class="col-md-10">
			<!-- 搜索表单begin -->
					<form action="${ctx}/banji?method=searchByCondition" method="post" id="searchForm">
					<input type="hidden" name="pageNo" id="pageNo">
						
						班级：<input type="text" name="name" value="${searchCondition.name}"/>
					
						<input type="submit" value="搜索"/>
					</form>
					<!-- 搜索表单end -->
					<table class="table table-hover">
				      <thead>
				        <tr>
				          <th>编号</th>
				          <th>班级</th>
				        </tr>
				      </thead>
				       <tbody>
				      	<c:forEach items="${pageBean.list}" var="banji">
					        <tr>
					          <td>${banji.id}</td>
					          <td>${banji.name}</td>
					          <td><a href="javascript:delbanji(${banji.id})" class="btn btn-warning" >删除</a></td>
			                  <td><a href="${ctx}/banji?method=toUpdate&id=${banji.id}" class="btn btn-info">修改</td>
					        </tr>
				      	</c:forEach>
				      </tbody>
				      </table>
				      <nav aria-label="Page navigation">
					  <ul class="pagination">
					  	<!-- 上一页 begin -->
					  	<c:if test="${pageBean.pageNo==1}">
						    <li class="disabled">
						      <a href="javascript:void(0)" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
					  	</c:if>
					  	<c:if test="${pageBean.pageNo!=1}">
						    <li>
						      <a href="javascript:goPage('${pageBean.pageNo-1}')" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
					  	</c:if>
					  	<!-- 上一页 end -->
					    
					    <c:forEach begin="1" end="${pageBean.totalPage}" var="page">
					    	<c:if test="${pageBean.pageNo!=page}">
						   		 <li><a href="javascript:goPage('${page}')">${page}</a></li>
					    	</c:if>
						    <c:if test="${pageBean.pageNo==page}">
						    	<li class="active"><a href="javascript:void(0)">${page}</a></li>
						    </c:if>
					    </c:forEach>
					    <c:if test="${pageBean.pageNo.equals(pageBean.totalPage)}">
						    <li class="disabled">
						      <a href="javascript:void(0)" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
					  	</c:if>
					    <c:if test="${!pageBean.pageNo.equals(pageBean.totalPage) }">
					    <li>
					      <a href="javascript:goPage('${pageBean.pageNo+1}')" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					    </c:if>
					  </ul>
					</nav>
				      
				</div>
				
				<!--右边部分 end-->
		</div>
		<!--内部内容 end-->
	</body>
</html>
