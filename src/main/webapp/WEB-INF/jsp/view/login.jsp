<%--
  Created by IntelliJ IDEA.
  User: joshc
  Date: 4/12/2024
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign-in genus</title>
</head>
<body>
<h2>Login</h2>
You must log in to access the website.<br><br>
<c:if test="${loginFailed == true}">
    <b><c:out value="The username or password you enteres are not correct, Please try again."></c:out></b><br>
</c:if>
<form:form method="POST" action="/login" modelAttribute="loginForm">
    Username: <input type="text" name="username"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="submit" value="Log In">
</form:form>

<c:choose>
    <c:when test="${not empty sessionScope.username and sessionScope.username == 'admin'}">
        <a href="${pageContext.request.contextPath}/session">Sessions</a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/signUp">Sign Up</a>
    </c:otherwise>
</c:choose>
</body>
</html>
