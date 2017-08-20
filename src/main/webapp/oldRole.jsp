<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="permit.model.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<logic:iterate id="rl" name="role">
		<logic:notEmpty name="rl" property="roleDescription" value="1">
			<input type="checkbox" name"roleId" value="${rl.roleId }" />
		</logic:notEmpty >
		<logic:empty name="rl" property="roleDescription" value="1">
			<input type="checkbox" name"roleId" value="${rl.roleId }" />
		</logic:empty>
	</logic:iterate>
</body>
</html>