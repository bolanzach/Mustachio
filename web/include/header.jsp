<%-- 
    Document   : header
    Created on : Nov 26, 2014, 4:25:27 PM
    Author     : Zach Bolan
--%>

<%@page import="bean.entity.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="home" class="bean.Home" scope="session"/>


<div class="container">

	<div class="title">
		<h1><a href="index.jsp">Mustachio</a></h1>
	</div>

	<div class="right">
		<%
			if (session.getAttribute("user") != null) {
				String user = session.getAttribute("user").toString();
				int imgId = home.getMember(user).getProfile_pic();
				out.print("<a href='uploadPhoto.jsp'><button class='upload-link' type='button'>Post Picture</button></a>");
				out.print("<a href='profile.jsp?user=" + user + "'>" + "<img class='pic' src='images/"+imgId + "'></a>");
			} else {
				out.print("<a href='login.jsp'>Hi Friend, Login</a>");
			}
		%>
	</div>
	<div class="clear"></div>
</div>
<div class="spacer"></div>



