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
<form:form method="POST" action="/login" >
    <input type="hidden" name="action" value="signup">
    <form:label path="username">Username:&nbsp;</form:label>
    <form:input path="username"/><br><br>
    <form:label path="password">Password:&nbsp;</form:label>
    <form:password path="password"/><br><br>
    <input type="submit" value="Sign Up">
</form:form>
</body>
</html>
