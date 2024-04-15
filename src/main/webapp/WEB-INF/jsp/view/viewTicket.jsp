<%@ page import="com.example.joshuahallassignment4.Ticket" %>
<%@ page import="com.example.joshuahallassignment4.Attachment" %>
<%--
  Created by IntelliJ IDEA.
  User: joshc
  Date: 4/8/2024
  Time: 03:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%
    String ticketId = (String)request.getAttribute("ticketId");
    Ticket ticket = (Ticket)request.getAttribute("ticket");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/login?logout=true">Logout</a>
<h2>Ticket #<%= ticketId %>: <%= ticket.getSubject()%>  </h2>
<i>Customer Name - <%= ticket.getCustomerName() %></i><br /><br />
<%= ticket.getBody() %><br /><br />
<%
    if(ticket.getNumberOfAttachments() > 0){
        %>Attachments: <%
        int i = 0;
        for(Attachment a : ticket.getAttachments()){
            if(i++ > 0)
                System.out.print(", ");
            %><a href="${pageContext.request.contextPath}/ticket?action=download&ticketId=<%= ticketId %>&attachment=<%= a.getName() %>"><%= a.getName() %></a><%
            }

        }

%>
<br>
<a href="${pageContext.request.contextPath}/ticket?action=list">Return</a>
</body>
</html>
