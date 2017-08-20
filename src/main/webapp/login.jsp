<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="javascript">
	function validate(){
		if(document.login.loginName.value==""){
			alert("对不起，账号不能为空~！");
			document.login.loginName.focus();
			return false;
		}
		if(document.login.loginName.value.length<8){
			alert("对不起，账号不能小于8位~！");
			document.login.loginName.focus();
			return false;
		}
		if(document.login.loginName.value.length>20){
			alert("对不起，账号不能大于20位！~");
			docuement.login.loginName.focus();
			return false;
		}
		
		if(document.login.password.value==""){
			alert("对不起，密码不能为空~！");
			document.login.password.focus();
			return false;
		}
		if(document.login.password.value.length<8){
			alert("对不起，密码不能小于8位~！");
			document.login.password.focus();
			return false;
		}
		if(document.login.password.value.length>20){
			alert("对不起，密码不能大于20位！~");
			docuement.login.password.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<center>
		<form onsubmit="return validate()" name="login" action="<%=basePath %>exam/login.do" method="post">
			<table>
				<tr>
					<td>请输入您的账号：</td>
					<td>
					<input name="loginName" class="input" onblur="return signUp()"
					onkeyup="value=value.replace(/[\W]/g,'')"
					nbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>请输入您的密码：</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="登录"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html:html>