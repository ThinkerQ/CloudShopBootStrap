<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蓝源Eloan-P2P平台->用户注册</title>
<link rel="stylesheet" href="/js/bootstrap-3.3.2-dist/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="/css/core.css" type="text/css" />
<script type="text/javascript" src="/js/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/js/bootstrap-3.3.2-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
<script type="text/javascript" src="/js/plugins/validata.js"></script>
<script type="text/javascript" src="/js/jquery.bootstrap.min.js"></script>

<style type="text/css">
	.el-register-form{
		width:600px;
		margin-left:auto;
		margin-right:auto;
		margin-top: 20px;
	}
	.el-register-form .form-control{
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
				url:"/SendVerifyCode/sendVerifyCode.screen",
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
		
		//表单校验
		$("#registerForm").validate({
			rules:{
				mobile:{
					required:true,
					isMobile:true,
					remote:{
						type:"POST",
						url:"/Register/checkUserByPhoneNumberIsExist.screen"
					}
				},
				password:{
					required:true,
					rangelength:[4,16]
				},
				confirmPwd:{
					required:true,
					rangelength:[4,16],
					equalTo:"#password"
				}

			},
			messages:{
				mobile:{
					required:"手机号必填",
					isMobile:"请输入有效的手机号",
					remote:"手机号已被注册"
				},
				password:{
					required:"请输入密码",
					rangelength:"密码长度为{0}至{1}位",
				},
				confirmPwd:{
					required:true,
					rangelength:"密码长度为{0}至{1}位",
					equalTo:"两次密码不相同"
				}
			},
			errorClass:"text-danger",
			errorElement:"em",
			highlight:function(element,errorClass){
				$(element).closest("div.form-group").addClass("has-error");
			},
			unhighlight:function(element,errorClass){
				$(element).closest("div.form-group").removeClass("has-error");
			},
			submitHandler:function(){
				$("#registerForm").ajaxSubmit({
					success:function(data){
						if(data.success){
							$.messager.confirm("提示","注册成功！点击确定前去登陆",function(){
								window.location.href="/login.jsp";
							});
						}else{
							$.messager.alert(data.message);
						}
					}
				});
			}
		});
	})
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
				<span class="el-page-title">用户注册</span>
			</div>
		</div>
	</div>
	
	<!-- 网页内容 -->
	<div class="container">  
		<form id="registerForm" class="form-horizontal el-register-form"  action="/Register/register.screen" method="post" >
			<p class="h4" style="margin: 10px 10px 20px;color:#999;">请填写注册信息，点击“提交注册”即可完成注册！</p>
			<div class="form-group">
				<label class="control-label col-sm-2">手机号</label>
				<div class="col-sm-10">
					<input type="text" autocomplete="off" name="mobile" class="form-control" id="mobile"/>
					<p class="help-block">手机号为11位大陆手机号</p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">验证码</label>
				<div class="col-sm-10">
					<input type="text" autocomplete="off" name="verifycode" class="form-control" id="verifycode"/>
					<button id="sendVerifyCode" class="btn btn-success" type="button" autocomplate="off">发送验证码</button>
					<p class="help-block">输入验证码</p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">密&emsp;码</label>
				<div class="col-sm-10">
					<input type="password" autocomplete="off" name="password" id="password" class="form-control" />
					<p class="help-block">密码为4~16位字符组成,采用数字、字母、符号安全性更高</p>
				</div> 
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">确认密码</label>
				<div class="col-sm-10">
					<input type="password" autocomplete="off" name="confirmPwd" class="form-control" />
					<p class="help-block">请再次填写密码</p>	 
				</div>
			</div> 
			<div class="form-gorup">   
				<div class="col-sm-offset-2">  
					<button type="submit" class="btn btn-success">
						同意协议并注册
					</button>
					&emsp;&emsp;
					<a href="/login.jsp" class="text-primary">已有账号，马上登录</a>
					
					<p style="padding-left: 50px;margin-top: 15px;">
						<a href="#">《使用协议说明书》</a>
					</p>
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
					<a href="http://www.gzjd.gov.cn/wlaqjc/open/validateSite.do" style="color: #ffffff">穗公网安备：44010650010086</a>
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