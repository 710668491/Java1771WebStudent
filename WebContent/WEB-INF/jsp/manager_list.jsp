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
	function delStudent(id) {
		var isDel = confirm("您确认要删除么？");
		if(isDel) {
			location.href = "${ctx}/student?method=deleteStudent&id=" + id;  
		}
	}
	

	</script>
	<script type="text/javascript">

	function goPage(pageNo){
		$("#pageNo").val(pageNo);
		$("#searchForm").submit();
	}
	function selectAll(){
		$("input[name=selectIds]").prop("checked", $("#selectAlls").is(":checked"))
	}
	function deleteAll(){
		var isConfirmDelete = confirm("确认要删除么？");
	    if (isConfirmDelete) {
	        $("#mainForm").attr("action", "${ctx}/student?method=deleteAll");
	        $("#mainForm ").submit();
	    }

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
		      <a class="navbar-brand" href="student_list.html">教务系统</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li><a href="${ctx}/student?method=findstudent"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;学生管理 <span class="sr-only">(current)</span></a></li>
		        <li><a href="${ctx}/banji?method=findbanji"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a></li>
		        <li><a href="${ctx}/course?method=findcourse"><span class="glyphicon glyphicon-list" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a></li>
		         <li><a href="${ctx}/teacher?method=findteacher"><span class="glyphicon glyphicon-tags" aria-hidden="true"></span>&nbsp;&nbsp;教师管理</a></li>
		        <li class="active"><a href="${ctx}/manager?method=searchByCondition"><span class="glyphicon glyphicon-tags" aria-hidden="true"></span>&nbsp;&nbsp;教务管理</a></li>
		        <li><a href="${ctx}/user?method=getOnLinePage"><span class="glyphicon glyphicon-sunglasses" aria-hidden="true"></span>&nbsp;&nbsp;在线列表</a></li>
		      </ul>
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="${ctx}/login?method=logout"><span>${user.name}&nbsp;&nbsp; </span><span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;&nbsp;退出</a></li>
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
					  <a href="${ctx}/manager?method=getManagerPage" class="list-group-item active">学生信息</a>
					  <a href="${ctx}/manager?method=getManagerPage" class="list-group-item ">学生添加</a>
					</div>
				</div>
				<!--左边部分 end-->
				<div class="col-md-2">
					<div class="list-group">
					 <%--  <a href="${ctx}/student?method=findstudent" class="list-group-item active">学生列表</a>
					  <a href="${ctx}/student?method=getStduentAdd" class="list-group-item">添加</a> --%>
					</div>
				</div>
				<!--左边部分 end-->
				<!--右边部分 Benin-->
							
			<div class="col-md-10">
			<!-- 搜索表单begin -->
					<form action="${ctx}/manager?method=searchByCondition" method="post" id="searchForm">
					<input type="hidden" name="pageNo" id="pageNo">
						姓名：<input type="text" name="name" value="${searchCondition.name}"/>
						年龄：<input type="text" name="age" value="${searchCondition.age}"/>
						班级：<input type="text" name="banji" value="${searchCondition.banji}"/>
					           课程：<input type="text" name="course" value="${searchCondition.course}"/>
					
						<input type="submit" value="搜索"/>
					</form>
					<!-- 搜索表单end -->
					<form action="" id="mainForm" method="post">
					<table class="table table-hover">
				      <thead>
				        <tr>
				       
				          <th>学生姓名</th>
				          <th>学生年龄</th>
				          <th>班级姓名</th>
				          <th>课程名称</th>
				          <th>学生学分</th>
				        </tr>
				      </thead>
				           <tbody>
				      	<c:forEach items="${pageBean.list}" var="map">
					        <tr>
					        
					          <td>${map['s_name']} </td>
					          <td>${map['age']}</td>
					          <td>${map['b_name']}</td>
					          <td>${map['c_name']}</td>
					          <td>${map['credit']}</td>
					        </tr>
				      	</c:forEach>
				      </tbody>
				      </table>
				      </form>
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
