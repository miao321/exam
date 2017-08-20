<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<html:form action="/exam/modExamModule.do">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E6E6E6" class="m-1">
			<tbody>
				<tr bgcolor="#f7f7f7">
					<td width="42%" height="25" align="right" valign="middle" bgcolor="#FFFFFF">模块名称:</td>
					<td width="58%" align="left" valign="middle" bgcolor="#FFFFFF">
					<label>
						<html:hidden property="moduleId" value="${examModule.moduleId }"/>
						<html:text property="moduleName" value="${examModule.moduleName }" />
						<html:hidden property="moduleAlias" value="${examModule.moduleAlias }"/>
						<html:hidden property="pageShow" value="${pageShow }"/>
					</label>
					</td>
				</tr>
				<tr bgcolor="#f7f7f7">
					<td height="25" align="right" valign="middle" bgcolor="#FFFFFF">模块描述:</td>
					<td align="left" valign="middle" bgcolor="#FFFFFF">
						<html:text property="moduleDescription" value="${examModule.moduleDescription }" size="80" />
					</td>
				</tr>
				<tr bgcolor="#f7f7f7">
					<td height="30" align="center" valign="middle" colspan="2">
						<label>
							<input type="submit" name="submit" value="编辑" />&nbsp;
							<input type="button" value="返回" onclick="back()" />
						</label>
					</td>
				</tr>
			</tbody>
		</table>
		<br />
	</html:form>
</body>
</html:html>