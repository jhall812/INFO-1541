<%--
  Created by IntelliJ IDEA.
  User: joshc
  Date: 4/16/2024
  Time: 09:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<h2>Sign Up</h2>
<form method="POST" action="<c:url value='/login' />">
    <input type="hidden" name="action" value="signup">
    Username: <input type="text" name="username"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="submit" value="Sign Up">
</form>
</body>
</html>
