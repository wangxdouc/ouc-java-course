<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.ArrayList, ouc.javaweb.model.Image"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Images</title>
</head>
<body>

	<jsp:useBean id="images" class="java.util.ArrayList" scope="request" />

	<%
		for (int i = 0; i < images.size(); i++) {
			Image img = (Image) images.get(i);
	%>
	<p><%=img.getTitle()%>
		<%=img.getDescription()%></p>
	<%
		}
	%>

	<%-- 使用EL表达式 --%>
	<p>${images[0].title}</p>














</body>
</html>