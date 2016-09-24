<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="<%=basePath%>/view/js/jquery-1.4.2.js" type="text/javascript"></script>


<!-- 引入easyui css文件,不限顺序 -->
<link rel="stylesheet" href="<%=basePath%>/view/easyui/themes/default/easyui.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>/view/easyui/themes/icon.css" type="text/css" />
<!-- 引入js文件，有顺序限制 -->
<script type="text/javascript" src="<%=basePath%>/view/easyui/jquery.min.js" ></script>
<script type="text/javascript" src="<%=basePath%>/view/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="<%=basePath%>/view/easyui/locale/easyui-lang-zh_CN.js" ></script>
<script type="text/javascript" src="<%=basePath%>/view/js/md5.js"></script>


<script type="text/javascript">
</script>
