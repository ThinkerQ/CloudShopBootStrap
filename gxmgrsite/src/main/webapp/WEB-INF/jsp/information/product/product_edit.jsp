<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="/js/plugins/uploadify/jquery.uploadify.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#categoryId").val()==""){
			$("#categoryId").tips({
				side:3,
	            msg:'请选择分类',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#categoryId").focus();
			return false;
		}
		if($("#firstName").val()==""){
			$("#firstName").tips({
				side:3,
	            msg:'请输入主名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#firstName").focus();
			return false;
		}
		if($("#secondName").val()==""){
			$("#secondName").tips({
				side:3,
	            msg:'请输入副名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#secondName").focus();
			return false;
		}
		if($("#createTime").val()==""){
			$("#createTime").tips({
				side:3,
	            msg:'请输入创建时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#createTime").focus();
			return false;
		}
		if($("#status").val()==""){
			$("#status").tips({
				side:3,
	            msg:'请输入状态:0.使用;1.停用',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#status").focus();
			return false;
		}
		if($("#littleImgUrl").val()==""){
			$("#littleImgUrl").tips({
				side:3,
	            msg:'请输入小图URL',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#littleImgUrl").focus();
			return false;
		}
		if($("#bigImgUrl").val()==""){
			$("#bigImgUrl").tips({
				side:3,
	            msg:'请输入大图URL',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#bigImgUrl").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	function categorySelect() {
		top.jzts();
		var diag = new top.Dialog();
		diag.Drag=true;
		diag.Title ="选择分类";
		diag.URL = '<%=basePath%>pictures/goEdit.do';
		diag.Width = 450;
		diag.Height = 355;
		diag.CancelEvent = function(){ //关闭事件
			if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				nextPage(${page.currentPage});
			}
			diag.close();
		};
		diag.show();
	}

</script>
	</head>
<body>
	<form action="product/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="product_ID" id="product_ID" value="${pd.product_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商品分类:</td>
				<td>
					<select class="chzn-select" name="categoryId" id="categoryId" data-placeholder="请选择商品分类" style="vertical-align:top;"  title="分类">
						<c:forEach items="${categoryList}" var="va">
							<option value="${va.category_ID}" <c:if test="${va.category_ID == pd.categoryId }">selected</c:if>>${va.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">主名称:</td>
				<td><input type="text" name="firstName" id="firstName" value="${pd.firstName}" maxlength="32" placeholder="这里输入主名称" title="主名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">副名称:</td>
				<td><input type="text" name="secondName" id="secondName" value="${pd.secondName}" maxlength="32" placeholder="这里输入副名称" title="副名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">状态:</td>
				<td>
					<select name="status" title="状态">
						<option value="0" <c:if test="${pd.status == '0' }">selected</c:if> >正常</option>
						<option value="1" <c:if test="${pd.status == '1' }">selected</c:if> >停用</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">小图URL:</td>
				<td>
					<c:if test="${pd == null || pd.littleImgUrl == '' || pd.littleImgUrl == null }">
						<img alt="" src="" class="uploadImg" id="uploadImg1" style="height:100px;width:160px;" />
						<a href="javascript:;" id="uploadBtn1" >上传图片</a>
					</c:if>
					<c:if test="${pd != null && pd.littleImgUrl != '' && pd.littleImgUrl != null }">
						<a href="<%=basePath%>uploadFiles/uploadImgs/${pd.littleImgUrl}" target="_blank"><img src="<%=basePath%>uploadFiles/uploadImgs/${pd.littleImgUrl}" style="width:100px;width:160px;"/></a>
						<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.littleImgUrl}');" />
					</c:if>
					<input type="hidden" name="littleImgUrl" id="littleImgUrl" value="${pd.littleImgUrl }"/>
				</td>
			</tr>

			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">大图URL:</td>
				<td>
					<c:if test="${pd == null || pd.bigImgUrl == '' || pd.bigImgUrl == null }">
						<img alt="" src="" class="uploadImg" id="uploadImg2" style="width:100px;width:160px;"/>
						<a href="javascript:;" id="uploadBtn2" >上传正面</a>
					</c:if>
					<c:if test="${pd != null && pd.bigImgUrl != '' && pd.bigImgUrl != null }">
						<a href="<%=basePath%>uploadFiles/uploadImgs/${pd.bigImgUrl}" target="_blank"><img src="<%=basePath%>uploadFiles/uploadImgs/${pd.bigImgUrl}" style="width:100px;width:160px;"/></a>
						<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.bigImgUrl}');" />
					</c:if>
					<input type="hidden" name="bigImgUrl" id="bigImgUrl" value="${pd.bigImgUrl }"/>
				</td>
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
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			//上传
			$('.imgFile').ace_file_input({
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

			//把图片的a标签变成一个uploadify的组件
			$("#uploadBtn1").uploadify({
				auto:true,
				buttonText:"点击此上传商品小图",
				fileObjName:"file",
				fileTypeDesc:"亲，选择吧",
				fileTypeExts:"*.gif;*.jpg;*.png",
				multi:false,
				overrideEvents:['onUploadSuccess','onSelect'],
				swf:"/js/plugins/uploadify/uploadify.swf",
				uploader:"/product/uploadImage.do",
				//上传成功之后的回调
				onUploadSuccess:function(file,data,response){
					//data = $.evalJSON(data);
					$("#uploadImg1").attr("src","<%=basePath%>/uploadFiles/uploadImgs/"+data);
					$("#littleImgUrl").val(data);
				}

			});

			//把图片的a标签变成一个uploadify的组件
			$("#uploadBtn2").uploadify({
				auto:true,
				buttonText:"点击此上传商品大图",
				fileObjName:"file",
				fileTypeDesc:"亲，选择吧",
				fileTypeExts:"*.gif;*.jpg;*.png",
				multi:false,
				overrideEvents:['onUploadSuccess','onSelect'],
				swf:"/js/plugins/uploadify/uploadify.swf",
				uploader:"/product/uploadImage.do",
				//上传成功之后的回调
				onUploadSuccess:function(file,data,response){
					//data = $.evalJSON(data);
					$("#uploadImg2").attr("src","<%=basePath%>/uploadFiles/uploadImgs/"+data);
					$("#bigImgUrl").val(data);
				}

			});

			//删除图片
			function delP(PATH){
				if(confirm("确定要删除图片？")){
					var url = "/product/deltp.do?PATH="+PATH;
					$.get(url,function(data){
						if(data=="success"){
							alert("删除成功!");
							document.location.reload();
						}
					});
				}
			}

			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});

		</script>
</body>
</html>