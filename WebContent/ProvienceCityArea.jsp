<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="WEB-INF/commom/base.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"${ctx}/province",
			dataType:"json",
			success:function(data) {
				var html = "<option>--请选择--</option>";
				for(var i=0; i<data.length; i++) {
					html += "<option value=''+data[i].id+'>"+data[i].province+"</option>";
				}
				$("#province").html(html);
			},
			type:"POST"
		});
		
	});
	
	function selectCitys(obj) {
		//清空城市下拉框中内容，除了第一项之外
		$("#city option:gt(0)").remove();
		//清空区县下拉框中内容，除了第一项之外
		$("#area option:gt(0)").remove();
		
		var provienceId = $(obj).val();
		$.ajax({
			url:"${ctx}/city",
			data:"provienceId=" + provienceId, 
			dataType:"json",
			success:function(data) {
				var html = "<option>--请选择--</option>";
				for(var i=0; i<data.length; i++) {
					html += "<option value="+data[i].id+">"+data[i].city+"</option>";
				}
				$("#city").html(html);
			},
			type:"POST"
		});
	}
	
	function selectAreas(obj) {
		var areaId = $(obj).val();
		$.ajax({
			url:"${ctx}/area",
			data:"areaId=" + areaId, 
			dataType:"json",
			success:function(data) {
				var html = "<option>--请选择--</option>";
				for(var i=0; i<data.length; i++) {
					html += "<option value="+data[i].id+">"+data[i].area+"</option>";
				}
				$("#area").html(html);
			},
			type:"POST"
		});
	}




</script>
</head>
<body>
	省份：
	<select id="province" onchange="selectCitys(this)">
		<option>--请选择--</option>
		<option>山东省</option>
		<option>江苏省</option>
		<option>浙江省</option>
	</select>
	城市：
	<select id="city" onchange="selectAreas(this)"> 
		<option>--请选择--</option>
		<option>济南</option>
		<option>青岛</option>
		<option>烟台</option>
	</select>
	区县：
	<select id="area" >
		<option>--请选择--</option>
		<option>市北区</option>
		<option>市南区</option>
		<option>李沧区</option>
	</select>

</body>
</html>