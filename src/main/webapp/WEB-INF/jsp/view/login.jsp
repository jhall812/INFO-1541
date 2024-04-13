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
You must log in to access the blog website.<br><br>
<c:if test="${loginFailed == true}">
    <b><c:out value="The username or password you enteres are not correct, Please try again."></c:out></b><br>
</c:if>
<form method="POST" action="<c:url value='/login'/>">
    Username: <input type="text" name="username"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="submit" value="Log In">
</form>
</body>
</html>
