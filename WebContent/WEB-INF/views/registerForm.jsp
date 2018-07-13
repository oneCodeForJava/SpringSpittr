<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spittr</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>"/>
<style type="text/css">
	label.error{
		color:red;
	}
	div.errors{
		background-color:#ffcccc;
		border: 2px solid red;
	}
	input.error{
		background-color:#ffcccc;
	}
</style>
</head>
<body>
	<h1>Register</h1>
	<sf:form method="POST" commandName="spitter">
		<sf:errors path="*" element="div" cssClass="errors"></sf:errors>
		<sf:label path="firstName" cssErrorClass="error">First Name</sf:label>:
		<sf:input path="firstName" cssErrorClass="error"/><br>
		<sf:label path="lastName" cssErrorClass="error">Last Name</sf:label>:
		<sf:input path="lastName" cssErrorClass="error"/><br>
		<sf:label path="username" cssErrorClass="error">UserName</sf:label>:
		<sf:input path="username" cssErrorClass="error"/><br>
		<sf:label path="password" cssErrorClass="error">Password</sf:label>:
		<sf:password path="password" cssErrorClass="error"/><br>
		<input type="submit" value="Register"> 
	</sf:form>
	<!-- 
	<sf:form method="POST" commandName="spitter">
		First Name: <sf:input path="firstName"/><sf:errors path="firstName" cssClass="error"/><br>
		Last Name: <sf:input path="lastName"/><sf:errors path="lastName" cssClass="error"/><br>
		UserName: <sf:input path="username"/><sf:errors path="username" cssClass="error"/><br>
		Password: <sf:password path="password"/><sf:errors path="password" cssClass="error"/><br>
		<input type="submit" value="Register"> 
	</sf:form>
	 -->
	<!-- 
	<form method="POST">
		First Name: <input type="text" name="firstName"/><br/>
		Last Name: <input type="text" name="lastName" /><br/>
		UserName: <input type="text" name="username" /><br/>
		Password: <input type="password" name="password" /><br/>
		<input type="submit" value="Register"> 
	</form>
	 -->
</body>
</html>