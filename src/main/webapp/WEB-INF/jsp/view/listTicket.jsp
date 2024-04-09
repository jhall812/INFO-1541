<%--
  Created by IntelliJ IDEA.
  User: joshc
  Date: 4/8/2024
  Time: 03:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List Tickets</title>
</head>
<body>
<h1>List of Tickets</h1>
<if test="${not empty ticketDB}">
    <ul>
        <forEach var="ticketEntry" items="${ticketDB}">
            <li>
                <set var="ticketId" value="${ticketEntry.key}"/>
                <set var="ticket" value="${ticketEntry.value}"/>
                <a href="viewTicket?ticketId=${ticketId}">Ticket ID: ${ticketId}</a>
            </li>
        </forEach>
    </ul>
</if>
</body>
</html>
