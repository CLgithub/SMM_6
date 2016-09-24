<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/view/page/commons.jsp"%>
<head>
<title>目录管理页</title>
</head>
<body>
	<!-- 工具条开始 -->
	<div id="tb" >
		<span style="white-space:pre" ></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="onAdd()">增加</a>
		<span style="white-space:pre" ></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="onUpdate()">编辑</a>
		<span style="white-space:pre" ></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="onDelete()">删除</a>  
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
					<td>目录名称</td>
					<td><input class="easyui-textbox" type="text" id="menuname" name="menuname" data-options="required:true" ></input></td>
				</tr>
				<tr>
					<td>路径</td>
					<td><input class="easyui-textbox" type="text" id="menuurl" name="menuurl" ></input></td>
				</tr>
				<tr>
					<td>所属目录</td>
					<td>
						<input id="parentid"  name="parentid"  />
						<!-- <input class="easyui-textbox" type="text" id="parentid" name="parentid" ></input> -->
						<%-- <div class="easyui-panel" style="padding:5px">
							<ul id="tt" class="easyui-tree" 
								data-options="
									url:'<%=basePath%>sysMenuController/loadUserMenu.action',
									method:'post',
									animate:true"></ul>
						</div> --%>
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
	<script type="text/javascript">
		var treeGrid;//主目录树
		$(function(){
			treeGrid=$("#tg").treegrid({
				columns : [[ {
					field : 'id',
					width : '10%',
					title : 'id'
				}, {
					field : 'menuname',
					width : '30%',
					title : '目录名称'
				}, {
					field : 'menuurl',
					width : '30%',
					align : 'right',
					title : '路径'
				}, {
					field : '_parentId',
					width : '10%',
					align : 'right',
					title : '上级目录id'
				}, {
					field : 'status',
					width : '10%',
					align : 'right',
					title : '状态'
				}]],
				url:'<%=basePath%>sysMenuController/getPBBySearch.action',
				rownumbers: true,
				animate: true,
				method: 'post',
				idField: 'id',
				treeField: 'menuname',
				toolbar:'#tb',
				border:false,
				showFooter: true
			})
			getUserMenu();
		});
		//弹出新增框
		function onAdd(){
			$('#ff').form('load',{
				id:null,
				menuname:null,
				menuurl:null,
				parentid:null
			});
			$('#dlg').dialog('open');
			getUserMenu();
		}
		//提交新增或修改
		function submitFormff(){
			$("#ff").form("submit",{
				url : "<%=basePath%>sysMenuController/saveOrUpdate.action",
                type:"POST",
				onLoadError:function(){
					$.messager.alert("保存信息", "发生错误!", "error");
				},success: function (result) {
					if(result=="true"){
						$.messager.alert("保存信息", "保存成功", "info");
						$('#dlg').dialog('close');
					}else{
						$.messager.alert("保存信息", "保存失败", "info");
					}
					treeGrid.treegrid("reload");
				}
			});
		}
		//修改
		function onUpdate(){
			getUserMenu();
			var row = treeGrid.datagrid('getSelections');
			if(row.length!=1){
				$.messager.alert("提示信息", "请选择要修改的一条数据！！！", "info");
				return;
			}else{
				$('#dlg').dialog('open');
				$('#ff').form('load',{
					id:row[0].id,
					menuname:row[0].menuname,
					menuurl:row[0].menuurl,
					parentid:row[0]._parentId
				});
			}
		}
		//删除
		function onDelete(){
			var row = treeGrid.datagrid('getSelections');
			if(row.length==0){
				$.messager.alert("提示信息", "请选择要删除的数据！！！", "info");
				return;
			}else{
				$.messager.confirm('提升', '你确定要删除该项吗?', function(r){
					if (r){
						var id = row[0].id;
						$.ajax({
							url : "<%=basePath%>sysMenuController/deleteByID.action",
							data : "id="+id,
							dataType : "text",
							type : "post",
							success : function(data) {
								if(data=="true"){
									$.messager.alert("保存信息", "删除成功", "info");
								}else{
									$.messager.alert("保存信息", "删除失败", "info");
								}
								treeGrid.treegrid("reload");
							}
						});
					}
				});
			}
		}
		//加载菜单下啦框
		function getUserMenu(){
			$("#parentid").combotree({
				width:135,
				url: '<%=basePath%>commonController/getUserMenu.action',
				valueField:'id',
				textField:'text'
			});
		}
	</script>
</body>
</html>