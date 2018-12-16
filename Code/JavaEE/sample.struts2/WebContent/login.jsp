<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><s:text name="loginPage" /></title>
</head>
<body>
	<h2><s:text name="loginPage" /></h2>
	<s:form action="login">
		<s:textfield name="username" key="user" />
		<s:password name="password" key="pass" />
		<s:submit key="login" />
	</s:form>
</body>
</html>