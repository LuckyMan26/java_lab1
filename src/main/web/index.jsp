<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Goods</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            position: relative; /* Add position relative for positioning the basket */
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .goods-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            padding: 0;
            list-style: none;
        }

        .good {
            padding: 15px;
            border-radius: 8px;
            background-color: #f9f9f9;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
        }

        .good-name {
            font-weight: bold;
            color: #333;
        }

        .good-price {
            color: #555;
        }

        .add-to-cart-button {
            margin-top: auto;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .add-to-cart-button:hover {
            background-color: #45a049;
        }

        /* Basket styles */
        .basket-icon {
            position: absolute;
            top: 20px;
            right: 20px;
            cursor: pointer;
        }

        .basket-icon .badge {
            position: absolute;
            top: -8px;
            right: -8px;
            background-color: red;
            color: white;
            border-radius: 50%;
            padding: 5px;
            font-size: 12px;
            min-width: 18px;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>List of Goods</h1>
    <div class="goods-grid" id="goodsGrid"></div>
</div>

<!-- Basket icon -->
<div class="basket-icon" id="basketIcon">
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-shopping-cart">
        <circle cx="9" cy="21" r="1"></circle>
        <circle cx="20" cy="21" r="1"></circle>
        <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
    </svg>
    <span class="badge badge-pill badge-primary" id="cartItemCount">0</span>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    var cartItemCount = 0;

    function displayGoods(goods) {
        var goodsGridElement = document.getElementById('goodsGrid');

        // Clear existing content
        goodsGridElement.innerHTML = '';

        // Loop through the list of goods and create div containers
        goods.forEach(function(good) {
            var div = document.createElement('div');
            div.classList.add('good');
            div.innerHTML = '<div class="good-name">' + good.name + '</div>' +
                '<div class="good-price">$' + good.price + '</div>' +
            '<button class="add-to-cart-button" onclick="addToCart(\'' + good.name + '\', ' + good.price + ')">Add to Cart</button>';
            goodsGridElement.appendChild(div);
        });
    }

    function fetchData() {
        fetch('http://localhost:5454/GoodServlet')
            .then(response =>  response.json())
            .then(data => {
                displayGoods(data);
            })
            .catch(error => console.error('Error:', error));
    }
    function addToCart(name, price) {
        cartItemCount++;

        document.getElementById('cartItemCount').textContent = cartItemCount;
        console.log('Added ' + name + ' to cart. Price: $' + price);

    }


    window.onload = fetchData;
</script>
</body>
</html>
