<!DOCTYPE html>
<html lang="en">
<head>
	<%@ page contentType="text/html;charset=utf-8" language="java"%>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/favicon.ico"/>
	<link rel="bookmark" href="${pageContext.request.contextPath}/static/favicon.ico"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pintuer.css">
	<script src="${pageContext.request.contextPath}/static/js/pintuer.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
<title>学生信息管理系统登录</title>
<script type="text/javascript">
	function resetValue(){
		document.getElementById("account").value="";
		document.getElementById("password").value="";
	}
</script>
</head>
<body>
	<div align="center" style="padding-top: 50px;">
		<table  width="1200" height="500" background="${pageContext.request.contextPath}/static/images/login2.jpg" >
			<tr height="200">
				<td colspan="4"></td>
			</tr>
			<tr height="30">
				<td width="40%"></td>
				<td width="10%">账 号 ：</td>
				<td><input type="text"  name="account" id="account"/></td>
				<td width="30%"></td>
			</tr>
			<tr height="30">
				<td width="40%"></td>
				<td width="10%">密  码：</td>
				<td><input type="password" name="password" id="password"/></td>
				<td width="30%"></td>
			</tr>
			<tr height="30">
				<td width="40%"></td>
				<td width="10%">验证码:</td>
				<td><input style="float: left;" type="text" id="captcha" name="captcha"  placeholder="验证码"  maxlength="4" /></td>
				<td width="30%"></td>
			</tr>
			<tr height="30">
				<td width="40%"></td>
				<td width="10%"><img  id="code"src="${pageContext.request.contextPath}/captcha"/></td>
				<td><a id="captchaImage" href="javascript:changeImage();">看不清？换一张</a></td>
				<td width="30%"></td>
			</tr>
			<tr height="30">
				<td width="40%"></td>
				<td width="10%"> <input type="submit"  value="登录" onclick="login()"></td>
				<td><input type="button" value="重置" onclick="resetValue()"/></td>
				<td width="30%"></td>
			</tr>

		</table>
		<script>
            document.onkeydown = function(event){
                var e = event || window.event || arguments.callee.caller.arguments[0];
                if(e && e.keyCode==13){ // enter 键

                    login();
                }
            };

			function login() {
                var account=$("#account").val();
                var password=$("#password").val();
                if(account==""||account==null)
				{
                    alert("请输入账号");
                    return ;
				}
                if(password==""||password==null) {
                    alert("请输入密码");
                    return;
                }
                var code=$("#captcha").val();
                if(code==""){
                    alert("验证码不能为空")
                    return ;
                }
				$.ajax({
						type:'post',
						url:'${pageContext.request.contextPath}/login',
						data: "account="+account+"&password="+password+"&code="+code,
						success:function (data) {
						    if(data["one"]==1)
							{
							    alert("登录成功");
							    window.location.href="${pageContext.request.contextPath}/management/loginSuccess";
							}else if (data["zero"]==0) {
                                alert("登录失败");
                                window.location.href="${pageContext.request.contextPath}/management/loginFail";
							}else {
						        alert(data["mess"]);
                            }
						}

					})

            }
            function changeImage()
			{
                $('#code').attr("src", "${pageContext.request.contextPath}/captcha?timestamp=" + (new Date()).valueOf());
			}
            // $('#captchaImage').click(function()
            // {
            //
            // });


		</script>

	</div>

</body>
</html>
