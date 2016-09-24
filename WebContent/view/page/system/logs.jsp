<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/view/page/commons.jsp"%>
<head>
<title>日志管理页</title>
</head>
<body>
	<!-- 工具条开始 -->
	<div id="tb" >
		<span style="white-space:pre" ></span>
		&nbsp;&nbsp;开始时间：
			<input type="text" id="startTimeTxt" class="easyui-datetimebox"   />
		<span style="white-space:pre" ></span>
		&nbsp;&nbsp;结束时间：
			<input type="text" id="endTime" class="easyui-datetimebox"   />
		<span style="white-space:pre" ></span>
		&nbsp;&nbsp;操作结果：
			<select id="operresult" class="easyui-combobox" style="width: 100px;">
				<option value="all">全部</option>
				<option value="success" >成功</option>
				<option value="failure">失败</option>
			</select>
		<span style="white-space:pre" ></span>
		&nbsp;&nbsp;操作人：
			<input type="text" id="SearchOperator" class="easyui-searchbox" name="SearchOperator" searcher="doSearch" />
		<span style="white-space:pre" ></span>
		&nbsp;&nbsp;操作方法：
			<input type="text" id="SearchOpername" class="easyui-searchbox" name="SearchOpername" searcher="doSearch" />
		
		
	</div>
	<!-- 工具条结束 -->
	<!-- 主表格开始 -->
	<table id="tg" ></table>
	<!-- 主表格结束 -->
	<!-- 新增对话框结束 -->
	<script type="text/javascript">
		var mainGrid;//主表格
		$(function(){
			doSearch("","");
		});
		//查询
		function doSearch(value,name){
			var SearchOperator=document.getElementById("SearchOperator").value;
			var SearchOpername=document.getElementById("SearchOpername").value;
			var startTime = $('#startTimeTxt').datetimebox('getValue');
			var endTime = $('#endTime').datetimebox('getValue');
			var operresult=$('#operresult').combobox('getValue');
		//	alert(startTimeTxt);
		//	alert(operresult);
			mainGrid=$("#tg").datagrid({
				columns : [[ {
					field : 'operator',
					width : '25%',
					title : '操作人'
				},{
					field : 'opertime',
					width : '10%',
					title : '操作时间'
				}, {
					field : 'opername',
					width : '10%',
					title : '操作方法'
				},{
					field : 'operparams',
					width : '30%',
					title : '方法参数'
				},{
					field : 'operresult',
					width : '10%',
					title : '操作结果'
				},{
					field : 'resultmsg',
					width : '10%',
					title : '结果信息'
				}]],
				url:"<%=basePath%>sysLogsController/getPBBySearch.action?SearchOperator="
											+SearchOperator
						+"&SearchOpername="+SearchOpername
						+"&startTime="+startTime
						+"&endTime="+endTime
						+"&operresult="+operresult,
				rownumbers: true,
				animate: true,
				method: 'post',
				toolbar:'#tb',
				isField:"id",
				border:false,
			//	fit:true,//自动补全 
			//	singleSelect:1,
				title:"查看日子",//标题
			//	iconCls:"icon-save",//图标  
				pagination:true,//显示分页  
	            pageSize:20,//分页大小  
	            pageList:[10,20,50,100]//每页的个数  
			});
		}
	</script>
</body>
</html>