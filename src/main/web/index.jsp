<<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Goods</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
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
    </style>
</head>
<body>

<div class="container">
    <h1>List of Goods</h1>
    <div class="goods-grid" id="goodsGrid"></div>
</div>
<script>
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
        // Here you can implement adding the item to the cart
        alert('Added ' + name + ' to cart. Price: $' + price);
    }

    window.onload = fetchData;
</script>
</body>
</html>
