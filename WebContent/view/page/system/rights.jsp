<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/view/page/commons.jsp"%>
<head>
<title>权限管理</title>
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
					<td>权限名称</td>
					<td><input class="easyui-textbox" type="text" id="rightname" name="rightname"  ></input></td>
				</tr>
				<tr>
					<td>权限描述</td>
					<td><input class="easyui-textbox" type="text" id="rightdesc" name="rightdesc"  ></input></td>
				</tr>
				<tr>
					<td>权限URL</td>
					<td>
						<input class="easyui-textbox" type="text" id="righturl" name="righturl" data-options="required:true"></input>
					</td>
				</tr>
				<tr>
					<td>是否是公共权限</td>
					<td>
						<input class="easyui-combobox" id="common" name="common" 
							data-options="
								data:[{'value':'true','text':'是'},{'value':'false','text':'否'}],
								valueField:'value',
								textField:'text'
							"/>  
					</td>
				</tr>
				<tr>
					<td>所属菜单
					</td>
					<td>
						<input class="easyui-combotree" id="smid" name="smid"  data-options="
							url: '<%=basePath%>commonController/getUserMenu.action',
							valueField:'id',
							textField:'text'
						">
					</td>
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
		var mainGrid;//主表格
		var mgRow;
		var imgcheckbox = function (cellvalue, options, rowObject) {
		    return cellvalue ? 
		    		'<img src="<%=basePath%>/view/easyui/themes/icons/ok.png" alt="是" title="是" />'
					: '<img src="<%=basePath%>/view/easyui/themes/icons/no.png" alt="不是" title="不是" />';
		};
		var myText=function (cellvalue, options, rowObject) {
			return '<input class="easyui-combobox" name="bmgRowser" '
			+' data-options=" url: \'<%=basePath%>commonController/getUserMenu.action\','
			+'method: \'get\',valueField:\'id\','
			+' textField:\'text\',groupField:\'_parentId\'	">'
		};
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
					field : 'rightname',
					width : '10%',
					title : '权限名称'
				},{
					field : 'rightdesc',
					width : '20%',
					title : '权限描述'
				},{
					field : 'common',
					width : '10%',
					align : 'center',
					formatter: imgcheckbox,
					title : '是否是公共权限'
				},{
					field : 'menuName',
					width : '10%',
					title : '所属菜单'
				},{
					field : 'righturl',
					width : '25%',
					title : '权限URL'
				},{
					field : 'rightpos',
					width : '10%',
					title : '权限位'
				},{
					field : 'rightcode',
					width : '10%',
					title : '权限码'
				}]],
				url:"<%=basePath%>sysRightsController/getPBBySearch.action?searchWhere="+value,
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
	            pageSize:50,//分页大小  
	            pageList:[5,10,15,20,50,100]//每页的个数  
			});
		}
		//弹出新增框
		function onAdd(){
			$('#ff').form('load',{
				id:null,
				rightname:null,
				rightdesc:null,
				righturl:null,
				common:null,
				smid:null
			});
			$('#dlg').dialog('open');
		}
		//提交新增或修改
		function submitFormff(){
			$("#ff").form("submit",{
				url : "<%=basePath%>sysRightsController/saveOrUpdate.action",
                type:"POST",
				onLoadError:function(){
					$.messager.alert("保存信息", "发生错误!", "error");
				},success: function (result) {
					if(result=="true"){
						$.messager.alert("保存信息", "保存成功", "info");
						$('#dlg').dialog('close');
					}else{
						$.messager.alert("保存信息", "保存失败，1失败原因："+result, "info");
					}
					mainGrid.datagrid("reload");
				}
			});
		}
		//修改
		function onUpdate(){
			mgRow = mainGrid.datagrid('getSelections');
			if(mgRow.length!=1){
				$.messager.alert("提示信息", "请选择要修改的一条数据！！！", "info");
				return;
			}else{
				$('#dlg').dialog('open');
				$('#ff').form('load',{
					id:mgRow[0].id,
					rightname:mgRow[0].rightname,
					rightdesc:mgRow[0].rightdesc,
					righturl:mgRow[0].righturl,
					common:mgRow[0].common,
					smid:mgRow[0].smid
				});
			}
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
							url : "<%=basePath%>sysRightsController/deleteByIDs.action",
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
	</script>
</body>
</html>