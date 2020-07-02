<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add product</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <!-- Brand/logo -->
    <a class="navbar-brand" href="/">Home</a>

    <!-- Links -->
    <ul class="navbar-nav">
        <c:if test="${not empty user}">
            <li class="nav-item">
                <a class="nav-link" href="/all-products">Produkty</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/add-product">Dodaj produkt</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/users">Uzytkownicy</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/shopping-cart">Koszyk</a>
            </li>
        </c:if>
        <c:if test="${empty user}">
            <li class="nav-item">
                <a class="nav-link" href="/register">Register</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
        </c:if>
        <c:if test="${not empty user}">
            <li class="nav-item">
                <a class="nav-link" href="/logout">Logout</a>
            </li>
        </c:if>

    </ul>
</nav>

<div class="container">

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Login</th>
            <th scope="col">Password</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="u">
            <tr>
                <td>${u.name}</td>
                <td>${u.surname}</td>
                <td>${u.login}</td>
                <td>${u.password}</td>

                <td>
                    <form method="post" action="/delete-user">
                        <input type="hidden" name="login" value="${u.login}">
                        <input type="submit" value="Delete user" />
                    </form>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>