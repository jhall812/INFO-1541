<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Support Menu</title>
</head>
<body>
<h1>Customer Support Menu</h1>

<ul>
    <li><a href="<c:url value="${pageContext.request.contextPath}/ticket-servlet?action=create" />">Create Ticket</a></li>
    <li><a href="<c:url value="/ticket-servlet?action=list" />">List Tickets</a></li>
    <li><a href="<c:url value="/ticket-servlet?action=view"/>">View Tickets</a></li>
    <li><a href="WEB-INF/jsp/view/viewTicket.jsp">view Tickets</a></li>
</ul>
</body>
</html>

