<%-- 
    Document   : sidePanel
    Created on : Nov 26, 2014, 9:06:17 PM
    Author     : Zach
--%>

<%@page import="bean.entity.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="bean.SidePanel" id="panelBean" scope="session" />

<%
	boolean isSearch = false;
	String search = (String) request.getParameter("search");
	if (search != null) isSearch = true;
%>


<div class="side-panel">
	
	<!-- SearchBar -->
	<form class="search-container" method="get" action="index.jsp">
		<input class="searchbar" type="text" name="search">
		<input class="searchbtn" type="submit" value="Search">
	</form>
	
	
	<%
		if (!isSearch) {
			for (Member member : panelBean.getAllTopMembers()) {
				out.println("<p><a href=\"profile.jsp?user="+member.getUsername()+"\">");
				out.println("<img class='pic' src='images/"+member.getProfile_pic()+"'>");
				out.println(member.getUsername());
				out.println("</a></p>");
			}
		}
		else {
			for (Member member : panelBean.getSearchResults(search)) {
				out.println("<p><a href=\"profile.jsp?user="+member.getUsername()+"\">");
				out.println("<img class='pic' src='images/"+member.getProfile_pic()+"'>");
				out.println(member.getUsername());
				out.println("</a></p>");
			}
		}
	%>
	
	
	
	
	
</div>
