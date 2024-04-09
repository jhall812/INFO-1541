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
    <meta charset="UTF-8">
    <title>Ticket Form</title>
</head>
<body>
<h1>Create New Ticket</h1>
<form action="ticket-servlet" method="post" enctype="multipart/form-data">
    <label for="customerName">Customer Name:</label>
    <input type="text" id="customerName" name="customerName" required><br><br>

    <label for="subject">Subject:</label>
    <input type="text" id="subject" name="subject" required><br><br>

    <label for="body">Body:</label><br>
    <textarea id="body" name="body" rows="4" cols="50" required></textarea><br><br>

    <label for="attachment">Attachment:</label>
    <input type="file" id="attachment" name="file1"><br><br>

    <input type="hidden" name="action" value="create">
    <input type="submit" value="Submit">
</form>
</body>
</html>
