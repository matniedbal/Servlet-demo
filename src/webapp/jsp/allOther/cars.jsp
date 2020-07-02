<%@ page import="eu.mrndesign.matned.servletDemo.allOtherSht.Person" %>
<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Home page</title>
</head>
<body>
<h1>
    <%
        out.print(request.getAttribute("cars"));
    %>
</h1>
</body>
</html>