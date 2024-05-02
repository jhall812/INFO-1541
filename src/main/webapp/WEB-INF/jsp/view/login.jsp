<%--suppress CheckTagEmptyBody --%>
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
    <b><c:out value="The username or password you have entered are not correct, Please try again."></c:out></b>
</c:if>
<form:form method="POST" action="login" modelAttribute="loginForm">
    <form:label path="username">Username:&nbsp;</form:label>
    <form:input path="username"/><br><br>
    <form:label path="password">Password:&nbsp;</form:label>
    <form:password path="password"/><br><br>
    <input type="submit" value="Log In">
</form:form>

<c:choose>
    <c:otherwise>
        Do not have an account <a href="${pageContext.request.contextPath}/ticket/signUp">Sign Up</a> here.
    </c:otherwise>
</c:choose>
</body>
</html>
