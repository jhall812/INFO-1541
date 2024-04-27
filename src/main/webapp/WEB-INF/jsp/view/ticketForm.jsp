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
    <h2>Create a Ticket</h2>
    <form:form method="POST" action="create" modelAttribute="ticket" enctype="multipart/form-data">
        <form:label path="CustomerName">Name:</form:label><br>
        <form:input path="customerName"/><br><br>
        <form:label path="subject">Subject:</form:label><br>
        <form:input path="subject"/><br><br>
        <form:label path="body">Body:</form:label><br>
        <form:textarea path="body" rows="10" cols="25"/><br><br>
        <b>Image</b><br>
        <form:input path="attachment" type="file"/><br><br>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>