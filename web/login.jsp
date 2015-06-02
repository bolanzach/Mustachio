<%-- 
    Document   : login
    Created on : Nov 25, 2014, 6:06:25 PM
    Author     : Zach Bolan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%--	<%
		if(!request.isSecure()) {
			response.sendRedirect("https://" + request.getServerName() + ":8443" + request.getContextPath() + "/login.jsp");
		}
	%>--%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="rsc/header.css" rel="stylesheet" type="text/css">
		<link href='http://fonts.googleapis.com/css?family=Grand+Hotel' rel='stylesheet' type='text/css'>
		<link href="rsc/style.css" rel="stylesheet" type="text/css">
        <title>Login</title>
		
    </head>
    <body>
		<jsp:include page="include/header.jsp" />
		<div class="wrapper">

			<div class="login-body">
				<form action="Login" method="post">
					<p>Username: <input type="text" name="username"></p>
					<p>Password: <input type="password" name="password"></p>
					<p><input type="submit" name="login" value="Login"> <input type="submit" name="register" value="Create Account"</p>
				</form>
			</div>
		</div>
    </body>
</html>
