<%--
  Created by IntelliJ IDEA.
  User: ArtemPC
  Date: 15.03.2024
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Cute Online Store</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        header {
            background-color: #66ccff;
            color: #333;
            padding: 20px 0;
            text-align: center;
        }
        .navbar {
            background-color: #66ccff;
        }
        .navbar-brand {
            color: #333;
            font-weight: bold;
        }
        .navbar-nav .nav-link {
            color: #333;
        }
        .product {
            background-color: #fff;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .product img {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .product h2 {
            margin-top: 0;
            font-size: 1.2em;
        }
        .product p {
            color: #666;
        }
        .product button {
            background-color: #66ccff;
            color: #333;
            border: none;
            padding: 8px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .product button:hover {
            background-color: #99ccff;
        }
    </style>
</head>
<body>
<header>
    <h1>Welcome to My Cute Online Store</h1>
</header>
<nav class="navbar navbar-expand-lg navbar-light">
    <div class="container">
        <a class="navbar-brand" href="#">Cute Store</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Shop</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">About Us</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<button class="btn btn-primary mt-3" id="addProductBtn">Add Product</button>
<div class="container mt-4" id="container">
    <div class="row" id="productRow">
        <!-- Your existing product cards -->
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>

    fetch('/goods')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Process the JSON data here
            console.log(data);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
</script>
</body>
</html>