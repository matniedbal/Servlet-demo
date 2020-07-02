<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add prod page</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
    <h1>Add products</h1>
    <c:if test="${not empty error}">
        <h2 style="color: red">Error while submiting form :
                ${error}
        </h2>
        <br>
    </c:if>
    <form method="post" action="/add-product">
        <div class="form-group">
            <label for="name">Product name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
        </div>
        <div class="form-group">
            <label for="category">Select product type:</label>
            <select id="category" class="form-control" name="category" size="1">
                <option>ELECTRONICS</option>
                <option>HEALTH</option>
                <option>HOME</option>
                <option>SPORT</option>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Product description:</label>
            <input type="text" class="form-control" id="description" placeholder="Enter description" name="description">
        </div>
        <div class="form-group">
            <label for="price">Product price:</label>
            <input type="text" class="form-control" id="price" placeholder="Enter price" name="price">
        </div>
        <div class="form-group">
            <label for="quantity">Product quantity:</label>
            <input type="text" class="form-control" id="quantity" placeholder="Enter quantity" name="quantity">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
</body>
</html>