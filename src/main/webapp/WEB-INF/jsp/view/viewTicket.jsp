<%@ page import="com.example.joshuahallassignment4.Ticket" %>
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
<a href="<c:url value="/login?logout" />">Logout</a>
<h2>Ticket #${ticketId}: <c:out value="${ticket.subject}" /></h2>
<i>Customer Name - ${ticket.customerName}</i><br /><br />
${ticket.body} <br /><br />

<c:if test="${ticket.numberOfAttachments > 0}">
    Attachments:
    <c:forEach items="${ticket.attachments}" var="attachment" varStatus="status">
        <c:if test="${!status.first}">, </c:if>
        <a href="<c:url value="/ticket">
                <c:param name="action" value="download" />
                <c:param name="ticketId" value="${ticketId}" />
                <c:param name="attachment" value="${attachment.name}" />
            </c:url>"><c:out value="${attachment.name}" /></a>
    </c:forEach><br /><br />
</c:if>

<a href="${pageContext.request.contextPath}/ticket?action=list">">Return to list tickets</a>
</body>
</html>
