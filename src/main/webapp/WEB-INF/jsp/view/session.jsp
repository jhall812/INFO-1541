<%--
  Created by IntelliJ IDEA.
  User: joshc
  Date: 4/15/2024
  Time: 01:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="now" value="<%=System.currentTimeMillis()%>" />
<html>
<head>
    <title>Customer Support admin view</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/login">Login</a>
<a href="${pageContext.request.contextPath}/login?logout=true">Logout</a>
<h2>Sessions</h2>
There are a total of <c:out value="${numSessions}"/> active sessions going on.
<ul>
    <c:forEach items="${sessionList}" var="s">
        <li>
            <c:out value="${session.id}"/> -
            <c:if test="${not empty sessionScope[session.id].username}">
                <c:out value="${sessionScope[session.id].username}"/> -
            </c:if>
            last active <c:out value="${(now - session.lastAccessedTime) / 1000}"/> seconds ago
        </li>
    </c:forEach>
</ul>

</body>
</html>
