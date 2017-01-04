<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/view/page/commons.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎登录系统</title>
</head>
<body>
<div id="menu">
	<%-- <a href="<%=basePath%>view/page/system/menu.jsp" target="rightFrame">菜单管理</a><br> --%>
	<%-- <a href="<%=basePath%>view/page/system/user.jsp" target="rightFrame">用户管理</a><br> --%>
	<%-- <a href="<%=basePath%>view/page/datagridTest/userJQassets.jsp" target="rightFrame">用户管理2</a><br>
	<a href="<%=basePath%>view/page/system/rights.jsp" target="rightFrame">权限管理</a><br>
	<a href="<%=basePath%>view/page/system/logs.jsp" target="rightFrame">日至管理</a><br> --%>
</div>
<div id="menu1"></div>
</body>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url : "<%=basePath%>commonController/loadUserMenu.action",
			dataType : "text",
			type : "post",
			success : function(data) {
				var dataObj = eval("(" + data + ")");
//				alert(dataObj[1].menuname);
				$.each(dataObj, function(index, item){
					document.getElementById("menu").innerHTML+=
						'<a href=<%=basePath%>'+dataObj[index].menuurl+' target="rightFrame">'+dataObj[index].menuname+'</a><br>'
				});
			}
		});
		$("menu1").accordion();
	});
</script>
</html>