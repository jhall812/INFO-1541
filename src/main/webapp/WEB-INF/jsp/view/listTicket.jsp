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
<a href="${pageContext.request.contextPath}/ticket/create">Create Ticket</a><br /><br />
<% if(ticketDatabase.size() == 0) { %>
<i>There are no tickets in the system.</i>
<% } else { %>
<% for(int id : ticketDatabase.keySet()) { %>
<%
    String idString = Integer.toString(id);
    Ticket ticket = ticketDatabase.get(id);
%>
Ticket #<%= idString %>: <a href="${pageContext.request.contextPath}/ticket/view/ticketId=<%= idString %>">
    <%= ticket.getSubject() %>
</a> (customer: <%= ticket.getCustomerName() %>)<br />
<% } %>
<% } %>

<%--<c:choose>--%>
<%--    <c:when test="${ticketDatabase.size() == 0}">--%>
<%--        <p>There are no tickets in the system.</p>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>
<%--        <c:forEach var="ticket" items="${ticketDatabase}">--%>
<%--            Blog#:&nbsp;<c:out value="${ticket.key}"/>--%>
<%--            <a href="<c:url value='/ticket/view/${ticket.key}'/>">--%>
<%--                <c:out value="${ticket.value.title}"/></a><br>--%>
<%--        </c:forEach>--%>
<%--    </c:otherwise>--%>
<%--</c:choose>--%>

</body>
</html>