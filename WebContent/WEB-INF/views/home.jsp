<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spittr</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>"/>
</head>
<body>
	<h1><s:message code="spittr.welcome"/></h1>
	<s:url value="/spittles" var="spittlesUrl">
		<s:param name="max" value="60" />
		<s:param name="count" value="15" />
	</s:url>
	<a href="${spittlesUrl}">Spittles</a>|
	<a href="<s:url value="/spitter/register"/>">Register</a>
</body>
</html>