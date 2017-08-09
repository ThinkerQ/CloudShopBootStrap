<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.guangxunet.shop.base.util.Constants"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	/* String commonImagesPath = "http://images.kaixinduobao.shop/"; */
	String commonImagesPath = Constants.COMMON_WEBSITE_ADDRESS;
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="static/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#no").val()==""){
			$("#no").tips({
				side:3,
	            msg:'请输入广告条编号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#no").focus();
			return false;
		}
		if(typeof($("#tpz").val()) == "undefined"){
			if($("#tp").val()=="" || document.getElementById("tp").files[0] =='请选择图片'){
				
				$("#tp").tips({
					    side:3,
			            msg:'请选图片',
			            bg:'#AE81FF',
			            time:3
			    });
				return false;
			}
		}
		if($("#location").val()==""){
			$("#location").tips({
				side:3,
	            msg:'请输入所在位置',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#location").focus();
			return false;
		}
		if($("#resolution").val()==""){
			$("#resolution").tips({
				side:3,
	            msg:'请输入分辨率',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#resolution").focus();
			return false;
		}
		if($("#detailUrl").val()==""){
			$("#detailUrl").tips({
				side:3,
	            msg:'请输入广告详情链接',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#detailUrl").focus();
			return false;
		}
		if($("#adsurl").val()==""){
			$("#adsurl").tips({
				side:3,
	            msg:'请输入上传图片',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#adsurl").focus();
			return false;
		}
		if($("#description").val()==""){
			$("#description").tips({
				side:3,
	            msg:'请输入广告描述',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#description").focus();
			return false;
		}
		if($("#title").val()==""){
			$("#title").tips({
				side:3,
	            msg:'请输入标题',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#title").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
	//过滤类型
	function fileType(obj){
		var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	    if(fileType != '.gif' && fileType != '.png' && fileType != '.jpg' && fileType != '.jpeg'){
	    	$("#tp").tips({
				side:3,
	            msg:'请上传图片格式的文件',
	            bg:'#AE81FF',
	            time:3
	        });
	    	$("#tp").val('');
	    	document.getElementById("tp").files[0] = '请选择图片';
	    }
	}
	
	//删除图片
	function delP(adsurl,advertisement_ID){
		 if(confirm("确定要删除图片？")){
			var url = "advertisement/deltp.do?adsurl="+adsurl+"&advertisement_ID="+advertisement_ID+"&guid="+new Date().getTime();
			$.get(url,function(data){
				if(data=="success"){
					alert("删除成功!");
					document.location.reload();
				}
			});
		} 
	}
</script>
	</head>
<body>
	<form action="advertisement/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="advertisement_ID" id="advertisement_ID" value="${pd.advertisement_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">广告条编号:</td>
				<td><input type="text" name="no" id="no" value="${pd.no}" maxlength="32" placeholder="这里输入广告条编号" title="广告条编号"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">标题:</td>
				<td><input type="text" name="title" id="title" value="${pd.title}" maxlength="32" placeholder="这里输入标题" title="标题"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">所在位置:</td>
				<td><input type="text" name="location" id="location" value="${pd.location}" maxlength="32" placeholder="这里输入所在位置" title="所在位置"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">分辨率:</td>
				<td><input type="text" name="resolution" id="resolution" value="${pd.resolution}" maxlength="32" placeholder="这里输入分辨率" title="分辨率"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">广告链接:</td>
				<td><input type="text" name="detailUrl" id="detailUrl" value="${pd.detailUrl}" maxlength="32" placeholder="这里输入广告链接" title="广告链接"/></td>
			</tr>
			<tr>
				<th>广告图片:</th>
				<td>
					<c:if test="${pd == null || pd.adsurl == '' || pd.adsurl == null }">
					<input type="file" id="tp" name="tp" onchange="fileType(this)"/>
					</c:if>
					<c:if test="${pd != null && pd.adsurl != '' && pd.adsurl != null }">
<%-- 						<a href="<%=basePath%>uploadFiles/uploadImgs/${pd.adsurl}" target="_blank"><img src="<%=basePath%>uploadFiles/uploadImgs/${pd.adsurl}" width="210"/></a> --%>
						<a href="<%=commonImagesPath%>${pd.adsurl}" target="_blank"><img src="<%=commonImagesPath%>${pd.adsurl}" width="210"/></a>
						<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.adsurl}','${pd.advertisement_ID}');" />
						<input type="hidden" name="tpz" id="tpz" value="${pd.adsurl }"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">广告描述:</td>
				<td><input type="text" name="description" id="description" value="${pd.description}" maxlength="32" placeholder="这里输入广告描述" title="广告描述"/></td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script src="static/assets/js/ace/elements.fileinput.js"></script>
		<script src="static/assets/js/ace/ace.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
			//上传
			$('#tp').ace_file_input({
				no_file:'请选择图片 ...',
				btn_choose:'选择',
				btn_change:'更改',
				droppable:false,
				onchange:null,
				thumbnail:false, //| true | large
				whitelist:'gif|png|jpg|jpeg',
				//blacklist:'gif|png|jpg|jpeg'
				//onchange:''
				//
			});
				
		});
		
		</script>
</body>
</html>