<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="permit.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function del(){
		if(confirm("你确定要删除吗？")){
			return true;
		}else{
			return false;
		}
	}
	function update(){
		if(confirm("你确定要更新吗？")){
			return true;
		}else{
			return false;
		}
	}
	function jump(){
		var pageShowTxt = document.module.pageShowTxt.value;
		var i=<%=pageCount %>
		if(pageShowTxt <= 0){
			alert("页数不能小于或者等于0");
		}else if(pageShowTxt > i){
			alert("不能大于总页数");
		}
	}
</script>
	<%!
		int pageShow = 0;
		int rowCount = 0;
		int pageCount = 1;
	%>
<body>
	<%
		Integer ig = (Integer) request.getAttribute("pageShow");
		pageShow = ig.intValue();
		ig = (Integer)request.getAttribute("rowCount");
		rowCount = ig.intValue();
		ig = (Integer)request.getAttribute("pageCount");
		pageCount = ig.intValue();
	%>
	
	<logic:iterate id="em" name="examModule">
		<%
		List list = (List)request.getAttribute("examModule");
		if(list != null){
			for(int i=0;i<list.size();i++){
				Object obj = list.get(i);
			}
		}
	%> 
	</logic:iterate>
	
	${em.moduleName}
</body>
</html>