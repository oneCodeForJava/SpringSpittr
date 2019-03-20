<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body onload="document.f.username.focus();">
<h3>Login with Username and Password</h3>
	<form name="f" action="/SpringSpittr/login" method="POST">
	 <table>
		  <tbody>
		  	<tr><td>User:</td><td><input type="text" name="username" value=""></td></tr>
		    <tr><td>Password:</td><td><input type="password" name="password"></td></tr>
		    <tr><td colspan="2"><input name="submit" type="submit" value="Login"></td></tr>
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		    <input id="remember_me" name="remember-me" type="checkbox"/>
		    <label for="remember_me" class="inline">Remember me</label>
		  </tbody>
	  </table>
	</form>
</body>
</html>