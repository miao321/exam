<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限系统</title>
</head>
<frameset rows="80,*,10" clos="*" frameborder="NO" border="0" framespacing="0">
	<frame src="top.jsp" name="topFrame" scrolling="auto" noresize >
	<frameset cols="200,*" frameborder="NO" border="1" framespacing="1">
		<frame src="left.jsp" scrolling="auto" frameborder="1" name="leftFrame" noresize>
		<frame src="main.jsp" scrolling="auto" frameborder="1" name="mainFrame" >
	</frameset>
	<frame scrolling="auto" frameborder="1" noresize >
</frameset>
<body>
	&nbsp;&nbsp;
	<center></center>
</body>
</html:html>