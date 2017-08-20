<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<logic:iterate id="source" name="resource">
		<logic:notEqual name="source" property="resourceDescription" value="1">
			<input type="checkbox" name="resourceAlias" value="${source.resourceAlias }">
		</logic:notEqual>
		<logic:equal  name="source" property="resourceDescription" value="1">
			<input type="checkbox" name="resourceAlias" value="${resourceAlias }" checked />		
		</logic:equal>
	</logic:iterate>
</body>
</html>