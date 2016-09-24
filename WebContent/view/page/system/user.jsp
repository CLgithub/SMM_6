<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/view/page/commons.jsp"%>
<head>
<title>用户管理页</title>
</head>
<body>
	<!-- 工具条开始 -->
	<div id="tb" >
		<span style="white-space:pre" ></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="onAdd()">增加</a>
		<span style="white-space:pre" ></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="onUpdate()">编辑</a>
		<span style="white-space:pre" ></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="setRight()">设置权限</a>
		<span style="white-space:pre" ></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="onDelete()">删除</a>  
		<span style="white-space:pre" ></span>
			<input type="text" class="easyui-searchbox" name="SearchWhere" searcher="doSearch" />
	</div>
	<!-- 工具条结束 -->
	<!-- 主表格开始 -->
	<table id="tg" ></table>
	<!-- 主表格结束 -->
	<!-- 新增对话框开始 -->
	<div id="dlg" class="easyui-dialog" closed="true" title="新增或修改" style="width:300px;height:300px;padding:10px; "
			data-options="
				modal:true,
				iconCls: 'icon-save',
				buttons: '#dlg-buttons'
			">
		<form id="ff" >
			<table cellpadding="5">
				<input type="hidden" id="id" name="id" ></input>
				<tr>
					<td>用户姓名</td>
					<td><input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true" ></input></td>
				</tr>
				<tr>
					<td>登录名</td>
					<td><input class="easyui-textbox" type="text" id="loginname" name="loginname" data-options="required:true" ></input></td>
				</tr>
				<tr>
					<td>号码</td>
					<td>
						<input class="easyui-textbox" type="text" id="number" name="number" ></input>
					</td>
				</tr>
				<tr>
					<td>所属部门</td>
					<td>
						<input id="sysdepcode"  name="sysdepcode"  />
					</td>
				</tr>
				<tr>
					<td>状态</td>
					<td><input class="easyui-textbox" type="text" id="status" name="status" value="1" ></input></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormff()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	<!-- 新增对话框结束 -->
	<!-- 设置权限对话框话框开始 -->
	<div id="dlg1" class="easyui-dialog" closed="true" title="设置权限" style="width:400px;height:400px;padding:10px; "
			data-options="
				modal:true,
				iconCls: 'icon-save',
				buttons: '#dlg-buttons'
			">
		<form id="ff1" >
			<table id="rightsGridT">
				<input type="hidden" id="uids" name="uids" ></input>
				<input type="hidden" id="rids" name="rids" ></input>
			</table>
			<%-- <table id="rightsGridT" class="easyui-datagrid" 
				data-options="
					border:false,
					collapsible:true,
					url:'<%=basePath%>sysRightsController/getPBBySearch.action?page=1&rows=9999&searchWhere='
				">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'rightname'" width="30%">权限名称</th>
						<th data-options="field:'rightdesc'" width="60%">权限描述</th>
					</tr>
				</thead>
			</table> --%>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormff1()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg1').dialog('close')">取消</a>
	</div>
	<!-- 设置权限对话框话框结束 -->
	<script type="text/javascript">
		var mainGrid;//主表格
		var mgRow;//主表格所选择的行
		var rightsGrid;//权限表格
		$(function(){
			doSearch("","");
		});
		
		//查询
		function doSearch(value,name){
			mainGrid=$("#tg").datagrid({
				columns : [[ {
					field : 'ck',
					checkbox:true
				},{
					field : 'name',
					width : '20%',
					title : '用户姓名'
				},{
					field : 'loginname',
					width : '20%',
					title : '登录名'
				},{
					field : 'number',
					width : '20%',
					title : '号码'
				},{
					field : 'depname',
					width : '20%',
					title : '所属部门'
				},{
					field : 'status',
					width : '10%',
					title : '状态'
				}]],
				url:"<%=basePath%>sysUserController/getPBBySearch.action?searchWhere="+value,
				rownumbers: true,
				animate: true,
				method: 'post',
				toolbar:'#tb',
				isField:"id",
				border:false,
			//	fit:true,//自动补全 
			//	singleSelect:1,
			//	title:"用户管理",//标题
				iconCls:"icon-save",//图标  
				pagination:true,//显示分页  
	            pageSize:10,//分页大小  
	            pageList:[5,10,15,20]//每页的个数  
			});
		}
		//弹出新增框
		function onAdd(){
			$('#dlg').dialog('open');
			$('#ff').form('load',{
				id:null,
				name:null,
				loginname:null,
				sysdepcode:null,
				number:null
			});
			getDep();
		}
		//弹出修改框
		function onUpdate(){
			mgRow = mainGrid.datagrid('getSelections');
			if(mgRow.length!=1){
				$.messager.alert("提示信息", "请选择要修改的一条数据！！！", "info");
				return;
			}else{
				getDep();
				$('#dlg').dialog('open');
				$('#ff').form('load',{
					id:mgRow[0].id,
					name:mgRow[0].name,
					loginname:mgRow[0].loginname,
					sysdepcode:mgRow[0].sysdepcode,
					number:mgRow[0].number
				});
			}
		}
		//提交新增或修改
		function submitFormff(){
			$("#ff").form("submit",{
				url : "<%=basePath%>sysUserController/saveOrUpdate.action",
                type:"POST",
				onLoadError:function(){
					$.messager.alert("保存信息", "发生错误!", "error");
				},success: function (result) {
					if(result=="true"){
						$.messager.alert("保存信息", "保存成功", "info");
						$('#dlg').dialog('close');
					}else{
						$.messager.alert("保存信息", "保存失败，失败原因："+result, "info");
					}
					mainGrid.datagrid("reload");
				}
			});
		}
		//打开设置权限对话框
		function setRight(){
			mgRow = mainGrid.datagrid('getSelections');
			if(mgRow.length==0){
				$.messager.alert("提示信息", "请选择要设置权限的人员！！！", "info");
				return;
			}else{
				$('#dlg1').dialog('open');//显示权限设置对话框
				loadRight();//加载权限表格
				var uids=null;
				for(var i=0;i<mgRow.length;i++){
					uids+=","+mgRow[i].id;
				}
				uids=uids.split("null,")[1];//得到要设置权限的人员id
				document.getElementById("uids").value=uids;
			}
		}
		//提交权限设置
		function submitFormff1(){
			var row = $('#rightsGridT').datagrid('getSelections');
			var rids=null;
			for(var i=0;i<row.length;i++){
				rids+=","+row[i].id;
			}
			if(row.length>0){
				rids=rids.split("null,")[1];
			}
			document.getElementById("rids").value=rids;
			$("#ff1").form("submit",{
				url : "<%=basePath%>sysRightsController/setRights.action",
                type:"POST",
				onLoadError:function(){
					$.messager.alert("保存信息", "发生错误!", "error");
				},success: function (result) {
					if(result=="true"){
						$.messager.alert("保存信息", "保存成功", "info");
						$('#dlg1').dialog('close');
					}else{
						$.messager.alert("保存信息", "保存失败，失败原因："+result, "info");
					}
					mainGrid.datagrid("reload");
					rightsGrid.datagrid("reload");
				}
			});
		}
		//删除
		function onDelete(){
			mgRow = mainGrid.datagrid('getSelections');
			if(mgRow.length==0){
				$.messager.alert("提示信息", "请选择要删除的数据！！！", "info");
				return;
			}else{
				$.messager.confirm('提示', '你确定要删除选中项吗?', function(r){
					if(r){
						var ids=null;
						for(var i=0;i<mgRow.length;i++){
							ids+=","+mgRow[i].id;
						}
						ids=ids.split("null,")[1];
						$.ajax({
							url : "<%=basePath%>sysUserController/deleteByIDs.action",
							data : "ids="+ids,
							dataType : "text",
							type : "post",
							success : function(data) {
								if(data=="true"){
									$.messager.alert("保存信息", "删除成功", "info");
								}else{
									$.messager.alert("保存信息", "删除失败，失败原因："+data, "info");
								}
								mainGrid.datagrid("reload");
							}
						});
					}
				});
			}
		}
		
		//加载权限表格
		function loadRight(){
			rightsGrid=$("#rightsGridT").datagrid({
				columns : [[ {
					field : 'ck',
					checkbox:true
				},{
					field : 'rightname',
					width : '40%',
					title : '权限名称'
				},{
					field : 'rightdesc',
					width : '50%',
					title : '权限描述'
				}]],
				url : "<%=basePath%>commonController/getRightByUser.action",
				rownumbers: true,
				animate: true,
				method: 'post',
				isField:"id",
				border:false,
				onLoadSuccess:function(data){
					//得到所选择的人员所拥有的权限，并加载到权限表格
					loadUserR(mgRow[0].id);
				}
			});
		}
		//加载用户权限到权限表格
		function loadUserR(uid){
			$.ajax({
				url : "<%=basePath%>commonController/getRightByUser.action",
				data : "uid="+uid,
				dataType : "text",
				type : "post",
				success : function(data) {
					var dataObj = eval("(" + data + ")");
					var allRow = rightsGrid.datagrid('getRows');
					$.each(dataObj, function(index2, item2){
					//	alert(item2.rightID);
						$.each(allRow, function(index1, item1){
					//		alert(item1.id);
							if(item1.id==item2.rightID){
								rightsGrid.datagrid('checkRow',index1);
							}
						});
					});
				}
			});
		}
		//加载部门下啦框
		function getDep(){
			$("#sysdepcode").combotree({
				width:135,
				url: '<%=basePath%>commonController/getUserDepList.action',
				valueField:'id',
				textField:'text'
			});
		}
	</script>
</body>
</html>