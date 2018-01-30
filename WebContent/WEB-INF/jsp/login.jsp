<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../commom/base.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
.YS{
width: 200px;
margin: 100px auto;

}
</style>
<script type="text/javascript">
	function refreshCode(){
		$("#codeImage").attr("src", "${ctx}/checkImg?" + Math.random());	
	}
</script>
</head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap-3.3.7-dist/css/bootstrap.css"/>
<body>
<form action="${ctx}/login?method=login" method="post">
	<div class="YS" >
		<div class="form-group">
			<label for="exampleInputPassword1">账户</label> <input type="text" name="name" class="form-control">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">密码</label> <input type="password" name="password" class="form-control">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">验证码</label> <input type="text" name="checkCode" class="form-control">
		</div>
		<img id="codeImage" alt="" src="${ctx}/checkImg" onclick="refreshCode()"><br/>
		<button type="submit" class="btn btn-primary">登入</button>

	</form>

	</div>

</body>
</html>