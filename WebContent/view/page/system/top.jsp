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
<div>
		xx系统
	<br>[${sysUser.name}]你好, <a href="#" onclick="logoutF()">[退出系统]</a>
</div>
</body>
<script type="text/javascript">
	function logoutF(){
		if(confirm("您确定要退出吗？")){
			$.ajax({
				url : "<%=basePath%>/loginController/doLogout.action",
				dataType : "text",
				type : "post",
				success : function(data) {
					if(data=="true"){
						window.top.location.href="<%=basePath%>index.jsp";
					}
				}
			});
		}
	}
</script>
</html>