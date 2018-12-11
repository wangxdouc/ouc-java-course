<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,ouc.javaweb.model.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
</head>
<body>
	<c:forEach var="item" items="<%=request.getAttribute(\"users\")%>">
		<c:out value="${item.name}" />
	</c:forEach>

<%--
	<%
		ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
	%>
	
	<%=it.next().getName()%>

	<%
		}
	%>
 --%>
</body>
</html>

<%--
JSP生成的Servlet路径 
/Users/xiaodong/Documents/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/work/Catalina/localhost/sample.servlet
--%>