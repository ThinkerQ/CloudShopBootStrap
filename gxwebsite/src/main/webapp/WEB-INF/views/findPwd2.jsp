<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云购商城->找回密码</title>
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
						/* $.messager.confirm("提示","短信验证码正确，下一步重新设置密码",function(){
							window.location.href="/FindPassword/findPwd3.screen?"+"phoneNumber=18211674995";
						}); */
							window.location.href="/FindPassword/findPwd3.screen?"+"phoneNumber="+data.result.phoneNumber;
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
				<span class="el-page-title">用户登录</span>
			</div>
		</div>
	</div>
	
	<!-- 网页内容 --> 
	<div class="container">  
		<form id="loginForm" class="form-horizontal el-login-form" action="/SendVerifyCode/verifyCode.screen" method="post" >
			<p class="h4" style="margin: 10px 10px 20px 110px;color:#999;">${phoneNumber} 找回密码  1.输入手机号  <font color="blue"> 2.输入验证码</font>   3.设置密码</p> 
			<div class="form-group">
				<label class="control-label col-sm-2">验证码</label>
				<div class="col-sm-10">
					<input  type="hidden" id="phoneNumber"  value=${phoneNumber} name="phoneNumber"/></br>
					<input type="text" id="verifyCode" autocomplete="off" name="verifyCode" class="form-control"  placeholder="输入验证码"/>
					<!-- <button id="sendVerifyCode" class="btn btn-success" type="button" autocomplate="off">发送验证码</button> -->
				</div> 
			</div>
			<div class="form-gorup">
				<div class="col-sm-offset-3">
					<button type="submit" class="btn btn-success" style="width: 100px;">
						下一步
					</button>
					&emsp;&emsp;
					<a href="/register.jsp">新用户，马上注册</a></br></br>
				</div>
			</div>
		</form>
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