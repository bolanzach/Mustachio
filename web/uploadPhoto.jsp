<%-- 
    Document   : uploadPhoto
    Created on : Dec 3, 2014, 12:26:13 AM
    Author     : Zach Bolan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="rsc/style.css" rel="stylesheet" type="text/css">
		<link href="rsc/header.css" rel="stylesheet" type="text/css">
        <title>Upload</title>
    </head>
    <body>
        <jsp:include page="include/header.jsp" />

		<div class="login-body">
			<form action="Upload" method="post" enctype="multipart/form-data">
				<input type="file" name="file">
				<input type="text" name="alt">
				<input type="submit" value="Upload Image">
			</form>
		</div>

    </body>
</html>
