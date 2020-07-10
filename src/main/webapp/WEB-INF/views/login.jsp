<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log in</title>
</head>
<body>

	<div align="center">


		<div align="center">
			<h1 align="center">Log in</h1>
			<!--  If User Does Not Exist -->
			<c:if test="${param.error != null}">
  				Login failed. 
  				<jsp:forward page="/error-page" />
  			
  				</c:if>
  				</div>
			<div>
			<!--  If User Log Outs -->
			<c:if test="${param.logout != null}">
  			<p style="color: red">You have been logged out!!!! </p>
			</c:if>
			</div>
			<h2>Please Log into your account</h2>
			<pre>
		
		<form action="${pageContext.request.contextPath}/do_login" method="post">
		Username : <input type="text" name="username" id="username"><br/>
		Password : <input type="password" name="password" id="password">
			<input type="submit" value="Login"/ >
			</form>
		</pre>

		</div>
</body>
</html>