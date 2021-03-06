<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>All products</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/">Home</a>

    <ul class="navbar-nav">
        <c:if test="${not empty user}">
            <li class="nav-item">
                <a class="nav-link" href="/all-products">Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/add-product">Add product</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/users">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/shopping-cart">Cart</a>
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
        <tr>
            <form method="post" action="/search-products">
                <label for="search-by-category">Category:</label>
                <select id="search-by-category" name="search-by-category">
                    <option>ALL CATEGORIES</option>
                    <option>ELECTRONICS</option>
                    <option>HEALTH</option>
                    <option>HOME</option>
                    <option>SPORT</option>
                </select>
                <label for="search-by"> Search:</label>
                <select id="search-by" name="search-by">
                    <option>name</option>
                    <option>description</option>
                </select>
                <br>
                <label for="min-price">Min price:</label>
                <input type="text" name="min-price" id="min-price" value=${minPrice}>
                <label for="max-price">Max price:</label>
                <input type="text" name="max-price" id="max-price" value=${maxPrice}>
                <br>
                <label for="min-quantity">Min quantity:</label>
                <input type="text" name="min-quantity" id="min-quantity" value=${minQuantity}>
                <label for="max-quantity">...Max quantity:</label>
                <input type="text" name="max-quantity" id="max-quantity" value=${maxQuantity}>
                <br>
                <input type="text" name="search" class="form-control-lg" id="search">
                <input type="submit" value="search" alt="search"/>
            </form>
        </tr>
        <thead>
        <tr>
            <th scope="col">Id
                <form method="post" action="/sort-products">
                    <input type="hidden" name="sorting" value="id">
                    <input type="submit" value="^" alt="sort by id"/>
                </form>
            </th>
            <th scope="col">Name
                <form method="post" action="/sort-products">
                    <input type="hidden" name="sorting" value="name">
                    <input type="submit" value="^" alt="sort by name"/>
                </form>
            </th>
            <th scope="col">Category
                <form method="post" action="/sort-products">
                    <input type="hidden" name="sorting" value="category">
                    <input type="submit" value="^" alt="sort by category"/>
                </form>
            </th>
            <th scope="col">Description
                <form method="post" action="/sort-products">
                    <input type="hidden" name="sorting" value="description">
                    <input type="submit" value="^" alt="sort by description"/>
                </form>
            </th>
            <th scope="col">Available quantity
                <form method="post" action="/sort-products">
                    <input type="hidden" name="sorting" value="quantity">
                    <input type="submit" value="^" alt="sort by quantity"/>
                </form>
            </th>
            <th scope="col">Price
                <form method="post" action="/sort-products">
                    <input type="hidden" name="sorting" value="price">
                    <input type="submit" value="^" alt="sort by price"/>
                </form>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.category}</td>
                <td>${p.description}</td>
                <td>${p.quantity}</td>
                <td>${p.price}</td>
                <td>
                    <form method="post" action="/edit-product">
                        <input type="hidden" name="id" value="${p.id}">
                        <input type="submit" value="Edit"/>
                    </form>
                </td>
                <td>
                    <form method="post" action="/delete-product" onsubmit="return confirm('Do you really want to delete the products?');">
                        <input type="hidden" name="id" value="${p.id}">
                        <input type="submit" id="deleteProductForm" name="submit" value="Delete"/>
                    </form>
                </td>
                <td>
                    <form method="post" action="/add-to-cart">
                        <input type="hidden" name="id" value="${p.id}">
                        <input type="submit" value="Add to cart"/>
                    </form>
                </td>
            </tr>
        </c:forEach>

        </tbody>
        <tr>
            <td>
                <form method="post" action="/first-page">
                    <input type="hidden" name="page" value="page">
                    <input type="submit" value="|<" alt="first page"/>
                </form>
            </td>
            <td>
                <form method="post" action="/prev-page">
                    <input type="hidden" name="page" value="page">
                    <input type="submit" value="<" alt="previous page"/>
                </form>
            </td>
            <td>
                <form method="post" action="/get-page">
                    <label for="page-no">Page:</label>
                    <input type="text" name="page" id="page-no" value=${currentAllProductsPage}>
                    <label for="max-per-page">Max results per page:</label>
                    <input type="text" name="max-per-page" id="max-per-page" value=${recordsOnAllProductsPage}>
                    <input type="submit" value="ok" alt="next page"/>
                </form>
            </td>
            <td></td>
            <td></td>
            <td>
                <form method="post" action="/next-page">
                    <input type="hidden" name="page" value="page">
                    <input type="submit" value=">" alt="last page"/>
                </form>
            </td>
            <td>
                <form method="post" action="/last-page">
                    <input type="hidden" name="page" value="page">
                    <input type="submit" value=">|"/>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>