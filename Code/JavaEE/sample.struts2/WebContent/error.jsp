<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="errorPage" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<s:text name="failTip" />
	<br>
	<s:actionerror />
</body>
</html>