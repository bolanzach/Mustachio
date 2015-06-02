<%-- 
    Document   : admin
    Created on : Dec 5, 2014, 10:08:19 PM
    Author     : Zach
--%>

<%@page import="bean.entity.Image"%>
<%@page import="bean.entity.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="bean.Admin" id="admin" scope="session"></jsp:useBean>
	<!DOCTYPE html>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<link href="rsc/style.css" rel="stylesheet" type="text/css">
			<link href="rsc/header.css" rel="stylesheet" type="text/css">
			<link href='http://fonts.googleapis.com/css?family=Grand+Hotel' rel='stylesheet' type='text/css'>
			<title>Admin</title>
		</head>
		<body>
		<jsp:include page="include/header.jsp" />
		<div class="center-txt">
			<h1>Administrator Access</h1>

			<table border="1">
				<tbody>
					<tr>
						<td>Username</td>
						<td>Images</td>
						<td>Is an Admin</td>
						<td>Profile Flagged</td>
					</tr>
					<%
						for (Member member : admin.getAllMembers()) {
							out.println("<tr>");
							out.println("<td>" + member.getUsername() + "</td>");
							out.println("<td>");
							for (Image img : admin.getImages(member)) {
								out.println("<img id='small-pic' src='images/" + img.getId() + "'>");
							}
							out.println("</td>");
							out.println("<td>" + admin.isAdmin(member) + "</td>");
							out.println("<td>" + admin.isFlagged(member) + "</td>");
							out.println("</tr>");
						}
					%>
				</tbody>
			</table>

		</div>

    </body>
</html>
