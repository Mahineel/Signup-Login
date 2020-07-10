<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page </title>
</head>
<body>
	<h3 align="center"> <p>Hello ${user } it seems like don't have access. kindly register first.</p>
	Sign Up <a href="${pageContext.request.contextPath}/signup"/>Click Here</h3>
	
</body>
</html>