<%--
  Created by IntelliJ IDEA.
  User: ArtemPC
  Date: 20.03.2024
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Store</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
        }
        .goods-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            grid-gap: 20px;
        }
        .goods-item {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        .goods-item img {
            max-width: 100%;
            height: auto;
        }
        .goods-item h3 {
            margin-top: 10px;
            margin-bottom: 5px;
        }
        .goods-item p {
            margin: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to Our Online Store</h1>
    <div class="goods-container" id="goods-container">
        <!-- Goods will be dynamically added here -->
    </div>
</div>

<script>


</script>
</body>
</html>

