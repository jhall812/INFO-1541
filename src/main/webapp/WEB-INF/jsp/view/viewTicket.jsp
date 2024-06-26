<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Customer Support</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
<h2>Ticket #${ticketId}: ${ticket.subject} </h2>
<i>Customer Name - ${ticket.customerName}</i><br /><br />
${ticket.body}<br /><br />
<c:if test="${ticket.numberOfAttachments > 0}">
    Attachments:
    <c:forEach var="attachment" items="${ticket.attachments}" varStatus="loop">
        <c:if test="${loop.index > 0}">
        </c:if>
        <a href="${pageContext.request.contextPath}/ticket/${ticketId}/image=${attachment.name}">${attachment.name}</a>
    </c:forEach>
</c:if>
<br>

<a href="${pageContext.request.contextPath}/ticket/list">Return</a>

</body>
</html>
