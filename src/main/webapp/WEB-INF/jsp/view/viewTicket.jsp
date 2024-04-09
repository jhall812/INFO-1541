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
    <title>View Ticket</title>
</head>
<body>
<h1>View Ticket</h1>
<if test="${ticket != null}">
    <p>Ticket ID: ${ticketId}</p>
    <p>Customer Name: ${ticket.customerName}</p>
    <p>Subject: ${ticket.subject}</p>
    <p>Body: ${ticket.body}</p>
    <if test="${ticket.numberOfAttachments > 0}">
        <h2>Attachments:</h2>
        <forEach var="attachment" items="${ticket.attachments}">
            <p>Attachment Name: ${attachment.name}</p>
            <!-- Display or download attachment link -->
        </forEach>
    </if>
</if>
</body>
</html>