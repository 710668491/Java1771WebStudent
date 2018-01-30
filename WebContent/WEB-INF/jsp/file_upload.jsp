<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../commom/base.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${ctx}/fileUpload"
		enctype="multipart/form-data" method="post">
		用户名：<input type="text" name="name"/><br/>
		密码：<input type="text" name="age"/><br/>
		文件：<input type="file" name="fileUpload"><br/>
		<input type="submit" value="提交"/>
	</form>

</body>
</html>