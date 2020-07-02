<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<html>
<head>
    <title>AutoMoto - Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Confirm your Car data:</h1>
    <form method="post" action="/automoto">
        <div class="form-group">
            <label for="ownername">Owner name:</label>
            <input type="text" class="form-control" id="ownername" placeholder="Enter owner name" name="ownername">
        </div>
        <div class="form-group">
            <label for="cartype">Select car type:</label>
            <select id="cartype" class="form-control" name="cartype" size="1">
                <option>hatchback</option>
                <option>sedan</option>
                <option>combi</option>
                <option>courier</option>
            </select>
        </div>
        <div class="form-group">
            <label for="brand">Brand:</label>
            <input type="text" class="form-control" id="brand" placeholder="Enter brand" name="vin">
        </div>
        <div class="form-group">
            <label for="model">Model:</label>
            <input type="text" class="form-control" id="model" placeholder="Enter model" name="model">
        </div>
        <div class="form-group">
            <label for="mileage">Mileage:</label>
            <input type="text" class="form-control" id="mileage" placeholder="Enter mileage" name="mileage">
        </div>
        <div class="form-group">
            <label for="color">Color:</label>
            <input type="text" class="form-control" id="color" placeholder="Enter color" name="color">
        </div>
        <div class="form-group">
            <label for="horsepower">Horse power:</label>
            <input type="text" class="form-control" id="horsepower" placeholder="Enter horse power" name="horsepower">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
</body>
</html>