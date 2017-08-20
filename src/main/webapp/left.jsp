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
	<logic:present name="manager">
		<logic:match name="manager" property="roleList" value=",bjlbgl,">
			<a class="item" target="mainFrame" href="/Exam/admin/showType.do">班级类别管理</a><br />
		</logic:match>
		<logic:match name="manager" property="roleList" value=",bjgl,">
			<a class="item" target="mainFrame" href="/Exam/admin/showClass.do">班级管理</a><br />
		</logic:match>
		<logic:match name="manager" property="roleList" value=",xxgl,">
			<a class="item" target="mainFrame" href="/Exam/admin/showStudent.do">学员管理</a><br />
		</logic:match>
		<logic:match name="manager" property="roleList" value=",tkgl,">
			<a class="item" target="mainFrame" href="/Exam/admin/showSubject.do">题库管理</a><br />
		</logic:match>
		<logic:match name="manager" property="roleList" value=",sjgl,">
			<a class="item" target="mainFrame" href="/Exam/admin/showPaper.do">试卷管理</a><br />
		</logic:match>
		<logic:match name="manager" property="roleList" value=",mkgl,">
			<a class="item" target="mainFrame" href="/Exam/admin/findAllExamModule.do">模块管理</a><br />
		</logic:match>
		<logic:match name="manager" property="roleList" value=",yhgl,">
			<a class="item" target="mainFrame" href="/Exam/admin/findAllExamManager.do">班级类别管理</a><br />
		</logic:match>
	</logic:present>
</body>
</html>