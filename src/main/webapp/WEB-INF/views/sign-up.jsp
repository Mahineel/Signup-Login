<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register User</title>
<style type="text/css">
.error {
	color: red
}
</style>
</head>
<body>

	<div align="center">
		<h1 align="center">Sign Up</h1>
		<c:if test="${not empty msg}">
   Error: ${msg}
</c:if>

		<pre>
		<form:form action="${pageContext.request.contextPath}/create-user"
				method="post" modelAttribute="User">
			
		Username : <form:input path="username" type="text" name="username" id="username" />
					<form:errors path="username" cssClass="error" />
	     EmailID : </tr> <td> <form:input type="email" path="emailId" name="email_id" id="email_id" />
								<form:errors path="emailId" cssClass="error" />
		Password : </tr> <td> <form:input type="password" path="password"
						name="password" id="password" />
<form:errors path="password" cssClass="error" />
		<input type="submit" value="Sign Up"/ >
</table>

		</
			</form:form>
		</pre>


	</div>



</body>
</html>