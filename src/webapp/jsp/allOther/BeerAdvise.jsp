<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Beer advice page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1 align="center">Doradza piwa</h1>
    <form method="post" action="/beer">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
        </div>
        <div class="form-group">
            <label for="beertype">Select beer type:</label>
            <select id="beertype" class="form-control" name="beertype" size="1">
                <option>light</option>
                <option>dark</option>
                <option>strong</option>
                <option>women</option>
            </select>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
</body>
</html>