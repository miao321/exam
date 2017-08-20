<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h3>对不起账号密码错误，请重新输入<br>
			几秒后程序将自动跳转
		</h3>
	</center>
	<%
		response.setHeader("refresh", "2;URL= login.jsp");
	%>
</body>
</html>