<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Support Menu</title>
</head>
<body>
<a href="<c:url value='/login'/>">Login</a>
<a href="<c:url value='/login'>
        <c:param name='logout'/>
    </c:url>">Logout</a>
<h1>Customer Support Menu</h1>
<ul>
    <li><a href= "${pageContext.request.contextPath}/ticket?action=create">Create Ticket</a></li>
    <li><a href="${pageContext.request.contextPath}/ticket?action=list">List Tickets</a></li>
<%--    <li><a href="${pageContext.request.contextPath}/ticket?action=view">View Tickets</a></li>--%>
</ul>
</body>
</html>

