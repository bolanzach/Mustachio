<%-- 
    Document   : index
    Created on : Nov 26, 2014, 4:24:19 PM
    Author     : Zach Bolan
--%>

<%@page import="bean.entity.Member"%>
<%@page import="bean.entity.Image"%>
<jsp:useBean class="bean.Home" id="home" scope="session" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="rsc/style.css" rel="stylesheet" type="text/css">
		<link href="rsc/header.css" rel="stylesheet" type="text/css">
		<link href='http://fonts.googleapis.com/css?family=Grand+Hotel' rel='stylesheet' type='text/css'>
        <title>Mustachio</title>
    </head>
	
    <body>
		<div class="wrapper">
			<jsp:include page="include/header.jsp" />
			<jsp:include page="sidePanel.jsp" />
			
			<%
				for (Image img : home.getNewestImages()) {
					// Image id of 1 is the default profile picture
					if (img.getId() != 1) {
						Member member = home.getMember(img.getOwner());
						out.println("<a href='profile.jsp?user="+member.getUsername()+"'><div class='post'>");
							out.println("<img src='images/"+img.getId()+"'>");
							out.println("<div class='menu'>");
								out.println("<img class='pic' src='images/"+member.getProfile_pic()+"'>");
							out.println("</div>");
						out.println("</div></a>");
					}
				}
			%>
			
			
			
		</div>

    </body>
</html>
