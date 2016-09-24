<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/view/page/commons.jsp"%>
<head>
	<title>登录页面</title>
</head>
<body>
	<table cellpadding="5" cellspacing="1" border="0" align="center">
		<tr>
			<td><b>用户名：</b></td>
			<td><input type="text" name="userName" id="userName" value="admin" style="width: 160px" /></td>
		</tr>
		<tr>
			<td><b>密码：</b></td>
			<td><input type="password" name="password" id="password" value="admin" style="width: 160px" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="button" onclick="loginF()" value="登录" /></td>
		</tr>
	</table>
</body>

<script type="text/javascript" >
	function loginF() {
		var userName = document.getElementById("userName").value;
		var password = hex_md5(document.getElementById("password").value);
		//alert(password);
		$.ajax({
			url : "<%=basePath%>/loginController/doLogin.action",
			data : "userName=" + userName + "&password=" + password,
			dataType : "text",
			type : "post",
			success : function(data) {
				if(data=="true"){
					window.top.location.href="<%=basePath%>view/page/system/main.jsp";
				}else{
					alert("用户名或密码错误");
				}
			}
		});
	}
</script>
</html>