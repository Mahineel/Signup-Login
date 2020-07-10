<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
	<h3>Hey Welcome User ${username }</h3>
	<h4>Your last login Date & Time  : <fmt:formatDate type="both" value="${user.lastLogin}" />  <br/>
	Your current login Date & Time : <fmt:formatDate type="both" value="${user.currentLogin}" /> </h4>
	
	<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>