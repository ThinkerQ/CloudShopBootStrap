<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云购商城->用户中心</title>
<link rel="stylesheet" href="/js/bootstrap-3.3.2-dist/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="/css/core.css" type="text/css" />
<script type="text/javascript" src="/js/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/js/bootstrap-3.3.2-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/jquery.bootstrap.min.js"></script>
<script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="/js/plugins/jquery-validation/localization/messages_zh.js"></script>
<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>

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
		//给发送短信按钮添加时间sendVerifyCode
		$("#sendVerifyCode").click(function(){
			var _this=$(this);
			_this.attr("disabled",true);
			//1,发送一个Ajax请求;
			$.ajax({
				url:"/SendVerifyCode/sendVerifyCodeForFindPwd.screen",
				dataType:"json",
				type:"POST",
				data:{phoneNumber:$("#mobile").val()},
				success:function(data){
					console.log(data);
					if(data.success){
						var sec=5;
						var timer=window.setInterval(function(){
							sec--;
							if(sec>0){
								_this.text(sec+"秒重新发送");
							}else{
								//去掉定时器
								window.clearInterval(timer);
								_this.text("重新发送验证码");
								_this.attr("disabled",false);
							}
						},1000);
					}else{
						$.messager.popup(data.message);
						_this.attr("disabled",false);
					}
				}
			});
		});
		
		
		$("#loginForm").validate({
			rules:{
				mobile:"required",
			}
			,
			messages:{
				mobile:"请输入手机号",
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
						$.messager.confirm("提示","密码重置成功！前确定去登陆",function(){
							window.location.href="/login.jsp";
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
				<span class="el-page-title">用户中心</span>
			</div>
		</div>
	</div>
	
	<!-- 网页内容 --> 
	<div class="container">  
			当前登录者：${currentLoginInfo.username}</p>
			<a onclick="window.location.href='/Login/goEdit.do';">修改用户信息</a>

		
	</div>
	
		
	
</body>
</html>