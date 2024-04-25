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
<a href="${pageContext.request.contextPath}/login?logout=true">Logout</a>
<body>
<h2>Sessions</h2>
There are a total of <c:out value="${numSessions}"/> active sessions going on.
<ul>
    <c:forEach items="${sessionList}" var="s">
        <li><c:out value="${s.id} - ${s.getAttribute('username')} - last active ${(now-s.getLastAccessedTime())/1000} seconds ago"/></li>
    </c:forEach>
</ul>

</body>
</html>
