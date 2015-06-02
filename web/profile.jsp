<%-- 
    Document   : profile
    Created on : Nov 28, 2014, 10:11:21 AM
    Author     : Zach Bolan
--%>

<%@page import="bean.entity.Image"%>
<%@page import="bean.entity.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="bean.Home" id="home" scope="session"></jsp:useBean>
	<!DOCTYPE html>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<link href="rsc/style.css" rel="stylesheet" type="text/css">
			<link href="rsc/header.css" rel="stylesheet" type="text/css">
			<link href='http://fonts.googleapis.com/css?family=Grand+Hotel' rel='stylesheet' type='text/css'>
			<title><%= request.getParameter("user")%>'s Profile</title>
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
		<jsp:include page="sidePanel.jsp" />

		<%
			boolean profileOwner = false;
			if (request.getParameter("user").equals(session.getAttribute("user"))) {
				profileOwner = true;
			}
		%>

		<div class="profile-header">
			<div class="left">
				<h1>
					<%
						out.println(request.getParameter("user"));
					%>
				</h1>
			</div>
			<div class="right">
				<h1>
					<%
						out.println(home.getMemberVotes(request.getParameter("user")));
					%>
				</h1>

				<%
					if (profileOwner) {
						out.println("<form action='Logout' method='post'>");
						out.println("<input type='submit' value='Logout' name='logout'>");
						out.println("</form>");
						String s = (String) session.getAttribute("user");
						Member m = home.getMember(s);
						if (m.isIsAdmin() == 1) {
							out.println("<a href='admin.jsp'><button class='upload-link' type='button'>Admin Page</button></a>");
						}

					} else if (session.getAttribute("user") != null) {
						out.println("<form action='DoVote' method='get'>");
						out.println("<input type='hidden' name='voteFor' value='" + request.getParameter("user") + "'>");
						out.println("<input type='submit' value='Vote' name='vote'>");
						out.println("</form>");

					}
				%>

			</div>
		</div>

		<div class="bio">
			<% out.println("<p>" + home.getMember(request.getParameter("user")).getBio() + "</p>");%>
		</div>
		
		<%
			for (Image img : home.getImages(home.getMember(request.getParameter("user")))) {
					// Image id of 1 is the default profile picture
					if (img.getId() != 1) {
						Member member = home.getMember(img.getOwner());
						out.println("<div class='post'>");
							out.println("<img src='images/"+img.getId()+"'>");
						out.println("</div>");
					}
				}
		%>



    </body>
</html>
