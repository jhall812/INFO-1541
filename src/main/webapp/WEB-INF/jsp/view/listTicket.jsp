<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: joshc
  Date: 4/8/2024
  Time: 03:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %>
<%@ page import="com.example.joshuahallassignment4.site.Ticket" %>
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
<a href="${pageContext.request.contextPath}/logout">Logout</a>
<h2>Tickets</h2>
<a href="${pageContext.request.contextPath}/ticket/create">Create Ticket</a><br /><br />
<c:choose>
    <c:when test="${empty ticketDatabase}">
        <i>There are no tickets in the system</i>
    </c:when>
    <c:otherwise>
        <c:forEach var="entry" items="${ticketDatabase}">
            <c:set var="idString" value="${entry.key}"/>
            <c:set var="ticket" value="${entry.value}"/>
            Ticket #<c:out value="${idString}"/>:
            <a href="${pageContext.request.contextPath}/ticket/view/${idString}">
                <c:out value="${ticket.subject}"/>
            </a>
            (customer: <c:out value="${ticket.customerName}"/><br />
        </c:forEach>
    </c:otherwise>
</c:choose>


</body>
</html>