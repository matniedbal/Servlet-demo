<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Login</title>
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
    <h1 align="center">Zaloguj się</h1>

    <c:if test="${not empty error}">
        <h2 style="color: red">Error while submiting form : ${error}</h2>
        <br>
    </c:if>

    <form method="post" action="/login">

        <div class="form-group">
            <label for="login">Login :</label>
            <input type="text" class="form-control" id="login" placeholder="Enter user login" name="login">
        </div>


        <div class="form-group">
            <label for="password">User password :</label>
            <input type="password" class="form-control" id="password" placeholder="Enter user password" name="password">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
</body>
</html>