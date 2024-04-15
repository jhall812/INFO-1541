<%--
  Created by IntelliJ IDEA.
  User: joshc
  Date: 4/8/2024
  Time: 03:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="false" import="java.util.Map" %>
<%@ page import="com.example.joshuahallassignment4.Ticket" %>
<%
    @SuppressWarnings("unchecked")
    Map<Integer, Ticket> ticketDatabase =
            (Map<Integer, Ticket>)request.getAttribute("ticketDatabase");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/login">Login</a>
<a href="${pageContext.request.contextPath}/login?logout=true">Logout</a>
<h2>Tickets</h2>
<a href="${pageContext.request.contextPath}/ticket?action=create">Create Ticket</a><br /><br />
<% if(ticketDatabase.size() == 0) { %>
<i>There are no tickets in the system.</i>
<% } else { %>
<% for(int id : ticketDatabase.keySet()) { %>
<%
    String idString = Integer.toString(id);
    Ticket ticket = ticketDatabase.get(id);
%>
Ticket #<%= idString %>: <a href="${pageContext.request.contextPath}/ticket?action=view&ticketId=<%= idString %>">
    <%= ticket.getSubject() %>
</a> (customer: <%= ticket.getCustomerName() %>)<br />
<% } %>
<% } %>

<a href="${pageContext.request.contextPath}/index.jsp">Menu</a>
</body>
</html>