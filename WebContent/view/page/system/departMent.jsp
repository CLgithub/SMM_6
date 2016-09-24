<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/view/page/commons.jsp"%>
<head>
<title>部门管理页</title>
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
				<input type="hidden" id="depcode" name="depcode" ></input>
				<tr>
					<td>部门名称</td>
					<td><input class="easyui-textbox" type="text" id="depname" name="depname" data-options="required:true" ></input></td>
				</tr>
				<tr>
					<td>所属部门</td>
					<td>
						<input id="parentcode"  name="parentcode"  />
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
					field : 'depcode',
					width : '10%',
					title : '部门编号'
				}, {
					field : 'depname',
					width : '70%',
					title : '部门名称'
				}, {
					field : 'status',
					width : '10%',
					align : 'right',
					title : '状态'
				}]],
				url:'<%=basePath%>sysDepartmentController/getPBBySearch.action?searchWhere=',
				rownumbers: true,
				animate: true,
				method: 'post',
				idField: 'depcode',
				treeField: 'depname',
				toolbar:'#tb',
				border:false,
				showFooter: true
			})
			getDep();
		});
		
		//弹出新增框
		function onAdd(){
			$('#dlg').dialog('open');
			$('#ff').form('load',{
				depcode:null,
				depname:null,
				parentcode:null,
			});
			getDep();
		}
		//提交新增或修改
		function submitFormff(){
			$("#ff").form("submit",{
				url : "<%=basePath%>sysDepartmentController/saveOrUpdate.action",
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
					treeGrid.treegrid("reload");
				}
			});
		}
		//修改
		function onUpdate(){
			var row = treeGrid.datagrid('getSelections');
			if(row.length!=1){
				$.messager.alert("提示信息", "请选择要修改的一条数据！！！", "info");
				return;
			}else{
				getDep();
				$('#dlg').dialog('open');
				$('#ff').form('load',{
					depcode:row[0].depcode,
					depname:row[0].depname,
					parentcode:row[0]._parentId
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
						var depcode = row[0].depcode;
						$.ajax({
							url : "<%=basePath%>sysDepartmentController/deleteByCode.action",
							data : "depcode="+depcode,
							dataType : "text",
							type : "post",
							success : function(data) {
								if(data=="true"){
									$.messager.alert("保存信息", "删除成功", "info");
								}else{
									$.messager.alert("保存信息", "删除失败，失败原因："+data, "info");
								}
								treeGrid.treegrid("reload");
							}
						});
					}
				});
			}
		}
		//加载部门下啦框
		function getDep(){
			$("#parentcode").combotree({
				width:135,
				url: '<%=basePath%>commonController/getUserDepList.action',
				valueField:'id',
				textField:'text'
			});
		}
	</script>
</body>
</html>