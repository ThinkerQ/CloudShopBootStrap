<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云购商城->用户信息</title>
<link rel="stylesheet" href="/js/bootstrap-3.3.2-dist/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="/css/core.css" type="text/css" />
<script type="text/javascript" src="/js/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/js/bootstrap-3.3.2-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/jquery.bootstrap.min.js"></script>
<script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="/js/plugins/jquery-validation/localization/messages_zh.js"></script>
<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
<script type="text/javascript" src="/js/plugins/uploadify/jquery.uploadify.min.js"></script>
<style type="text/css">
	.el-login-form{
		width:600px; 
		margin-left:auto;
		margin-right:auto;
		margin-top: 20px;
	}
	.el-login-form .form-control{
		width: 220px;
		display: inline;
	}
</style>

<script type="text/javascript">
	$(function(){
		//把图片的a标签变成一个uploadify的组件
		$("#uploadBtn1").uploadify({
			auto:true,
			buttonText:"点击此上传头像",
			fileObjName:"file",
			fileTypeDesc:"亲，选择吧",
			fileTypeExts:"*.gif;*.jpg;*.png",
			multi:false,
			overrideEvents:['onUploadSuccess','onSelect'],
			swf:"/js/plugins/uploadify/uploadify.swf",
			uploader:"/Login/editUserImage.do?id=26",
			//上传成功之后的回调
			onUploadSuccess:function(file,data,response){
				//data = $.evalJSON(data);
				$("#uploadImg2").attr("src","<%=basePath%>uploadFiles/uploadImgs/"+data);
				$("#littleImgUrl").val(data);
			}

		});
		
		$("#loginForm").validate({
			rules:{
				mobile:"required",
				password:"required"
			}
			,
			messages:{
				mobile:"请输入手机号",
				password:"请输入密码"
			},
			errorClass:"text-danger",
			errorElement:"em",
			highlight:function(element){
				$(element).closest("div.form-group").addClass("has-error");
			},
			unhighlight:function(element){
				$(element).closest("div.form-group").removeClass("has-error");
			},
		});
		$("#loginForm").ajaxForm({
			success:function(data){
				if(data.success){
					$.messager.confirm("提示","登陆成功，点击确定进入个人中心",function(){
						window.location.href="Login/personal.do";
					});
				}else{
					$.messager.popup(data.message);
				}
			}
		});
	});
	
</script>

</head>
<body>
	<!-- 网页头信息 -->
	<div class="el-header" >
		<div class="container" style="position: relative;">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/">首页</a></li>
				<li><a href="/login.html">登录</a></li>
				<li><a href="#">帮助</a></li>
			</ul>
		</div>
	</div>
	
	<!-- 网页导航 --> 
	<div class="navbar navbar-default el-navbar">
		<div class="container">
			<div class="navbar-header">
				<a href=""><img alt="Brand" src="/images/logo.png"></a>
				<span class="el-page-title">用户信息</span>
			</div>
		</div>
	</div>
	
	<!-- 网页内容 --> 
	<div class="container">
		  
			头像：
				<c:choose>
					<c:when test="${current.userImgUrl == null or current.userImgUrl == ''}">
						<img id="uploadImg1" src="<%=basePath%>images/sina.png" style="width:100px;height:100px"></img>
					</c:when>
					<c:otherwise>
						<img id="uploadImg2" src="<%=basePath%>uploadFiles/uploadImgs/${current.userImgUrl}" style="width:120px;height:50px"></img></p>
					</c:otherwise>
			 	</c:choose>
			 	</p>
			 	
		 	<a href="javascript:;" id="uploadBtn1" >上传图片</a></p> 
			用户ID：${current.id} </p> 
			昵称：${current.nickName}</p>
			手机号码：${current.mobile}</p>
	</div>
	
		
	<!-- 网页版权 -->
	<div class="container-foot-2">
		<div class="context">
			<div class="left">
				<p>专注于软件开发</p>
				<p>版权所有：&emsp;*****科技有限公司</p>
				<p>地&emsp;&emsp;址：&emsp;上海市浦东新区华夏东路益华路</p>
				<p>电&emsp;&emsp;话： 020-0000000&emsp;&emsp;
					邮箱：&emsp;king@gengshuqiang.com</p>
				<p>
					<a href="http://www.miitbeian.gov.cn" style="color: #ffffff">ICP备案
						：沪ICP备字1504547</a>
				</p>
				<p>
					<a href="http://www.gzjd.gov.cn/wlaqjc/open/validateSite.do" style="color: #ffffff">沪公网安备：44010650010086</a>
				</p>
			</div>
			<div class="right">
				<a target="_blank" href="http://weibo.com/ITxiaomage"><img
					src="/images/sina.png"></a>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>