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


        .basket-icon {

        }
        .icon-container {
            display: grid;
            grid-template-columns: auto auto; /* This will create two columns of equal width */
            justify-content: end;
            gap: 20px;
            position: absolute;
            top: 0;
            right: 20px;
            width: auto;
        }

        .basket-icon,
        .additem-icon {
            display: flex;
            align-items: center;
        }

        .additem-icon {
            justify-content: flex-end; /* Aligns the additem-icon to the right within its container */
        }


        .basket-icon {
            display: flex;
            align-items: center;
            position: relative;
        }

        .additem-icon {
            display: flex;
            align-items: center;
            justify-content: flex-end;
        }

        .basket-icon .badge {
            position: absolute;
            top: 0;
            right: -15px;
            background-color: red;
        }
        .modal-content {
            border-radius: 8px;
        }

        .modal-header {
            border-bottom: none;
        }

        .modal-footer {
            border-top: none;
        }
        #successMessage {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 1000;
            opacity: 0;
            transition: opacity 0.3s ease-in-out;
        }

        #successMessage.show {
            display: block;
            opacity: 1;
        }
        .pagination-container {
            display: flex;
            justify-content: flex-end; /* Align buttons to the right */
        }

        .pagination-container button {
            margin-left: 5px; /* Add some spacing between buttons */
        }
        .basket-items-container {
            position: absolute;
            top: 40px; /* Adjust as needed */
            right: 0;
            width: 200px; /* Adjust as needed */
            max-height: 200px; /* Limit the height */
            overflow-y: auto; /* Enable vertical scrolling */
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 10px;
            display: none; /* Hide by default */
        }

        .basket-items-container.show {
            display: block; /* Show the container */
        }

        .basket-items-container div {
            padding: 5px 0;
            border-bottom: 1px solid #ddd;
            font-size: 14px;
            color: #333;
        }

        .basket-items-container div:last-child {
            border-bottom: none;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>List of Goods</h1>
    <div class="goods-grid" id="goodsGrid"></div>
    <div class="pagination-container mt-3">
        <button id="previousPageButton" class="btn btn-secondary mr-2" disabled>Previous Page</button>
        <button id="loadMoreButton" class="btn btn-primary">Load More</button>
    </div>
</div>


<div class = "icon-container">

    <div class="basket-icon" onmouseover="displayBasketItems()">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-shopping-cart">
            <circle cx="9" cy="21" r="1"></circle>
            <circle cx="20" cy="21" r="1"></circle>
            <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
        </svg>
        <span class="badge badge-pill badge-primary" id="cartItemCount">0</span>
    </div>

    <!-- Basket Items List -->
    <div id="basketItemsContainer" class="basket-items-container">
        <!-- Basket items will be displayed here -->
    </div>

        <div class="additem-icon">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addItemModal" >
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                <path d="M8 1a.5.5 0 0 1 .5.5v13a.5.5 0 0 1-1 0v-13A.5.5 0 0 1 8 1zm7 7a.5.5 0 0 1-.5.5H2a.5.5 0 0 1 0-1h13a.5.5 0 0 1 .5.5z"/>
            </svg>
        </button>
    </div>
</div>

<!-- Add Item Modal -->
<div class="modal fade" id="addItemModal" tabindex="-1" role="dialog" aria-labelledby="addItemModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addItemModalLabel">Add New Item</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Form for adding new item -->
                <form id="addItemForm">
                    <div class="form-group">
                        <label for="itemName">Name</label>
                        <input type="text" class="form-control" id="itemName" required>
                    </div>
                    <div class="form-group">
                        <label for="itemPrice">Price</label>
                        <input type="number" class="form-control" id="itemPrice" required>
                    </div>
                    <div class="form-group">
                        <label for="itemQuantity">Quantity Available</label>
                        <input type="number" class="form-control" id="itemQuantity" required>
                    </div>
                    <div class="form-group">
                        <label for="itemDescription">Description</label>
                        <textarea class="form-control" id="itemDescription" rows="3"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="addItem()">Add Item</button>
            </div>
        </div>
    </div>
</div>
<div id="successMessage" class="alert alert-success fade" role="alert">
    Item successfully added!
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    var cartItemCount = 0;

    var currentPage = 1;
    var itemsPerPage = 15;
    var cartItemCount = 0;
    var cartItems = []; // Array to store added goods

    // Function to add item to the cart
    function addToCart(name, price) {
        cartItemCount++;
        cartItems.push({ name: name, price: price }); // Store added goods
        document.getElementById('cartItemCount').textContent = cartItemCount;
        console.log('Added ' + name + ' to cart. Price: $' + price);
    }

    // Function to display added goods on hover
    function displayBasketItems() {
        var basketItemsContainer = document.getElementById('basketItemsContainer');
        basketItemsContainer.innerHTML = ''; // Clear previous content

        if (cartItems.length === 0) {
            basketItemsContainer.textContent = 'No items added';
        } else {
            cartItems.forEach(function(item) {
                var itemElement = document.createElement('div');
                itemElement.textContent = item.name + ' - $' + item.price;
                basketItemsContainer.appendChild(itemElement);
            });
        }

        basketItemsContainer.classList.add('show'); // Show the container

        // Add event listener to hide basket items list when mouse moves away from both basket icon and basket items list
        basketItemsContainer.addEventListener('mouseleave', hideBasketItems);
    }

    // Function to hide the basket items list
    function hideBasketItems() {
        var basketItemsContainer = document.getElementById('basketItemsContainer');
        basketItemsContainer.classList.remove('show'); // Hide the container
    }
    function displayGoods(goods) {
        var goodsGridElement = document.getElementById('goodsGrid');

        // Clear existing content
        goodsGridElement.innerHTML = '';

        // Calculate start and end index for current page
        var startIndex = (currentPage - 1) * itemsPerPage;
        var endIndex = currentPage * itemsPerPage;

        // Loop through the list of goods and create div containers
        for (var i = startIndex; i < Math.min(endIndex, goods.length); i++) {
            var good = goods[i];
            var div = document.createElement('div');
            div.classList.add('good');
            div.innerHTML = '<div class="good-name">' + good.name + '</div>' +
                '<div class="good-price">$' + good.price + '</div>' +
                '<button class="add-to-cart-button" onclick="addToCart(\'' + good.name + '\', ' + good.price + ')">Add to Cart</button>';
            goodsGridElement.appendChild(div);
        }

        // Show or hide the load more button based on pagination
        var loadMoreButton = document.getElementById('loadMoreButton');
        if (endIndex < goods.length) {
            loadMoreButton.style.display = 'block';
        } else {
            loadMoreButton.style.display = 'none';
        }

        // Enable or disable the previous page button based on current page
        var previousPageButton = document.getElementById('previousPageButton');
        if (currentPage > 1) {
            previousPageButton.disabled = false;
        } else {
            previousPageButton.disabled = true;
        }
    }

    function loadMoreGoods() {
        currentPage++;
        setCurrentPageParam(currentPage);
        fetchData();
    }

    function previousPage() {
        if (currentPage > 1) {
            currentPage--;
            setCurrentPageParam(currentPage);
            fetchData();
        }
    }

    function fetchData() {
        fetch('/GoodServlet')
            .then(response =>  response.json())
            .then(data => {
                displayGoods(data);
            })
            .catch(error => console.error('Error:', error));
    }

    function addItem() {
        // Retrieve values from form
        var itemName = document.getElementById('itemName').value;
        var itemPrice = document.getElementById('itemPrice').value;
        var itemQuantity = document.getElementById('itemQuantity').value;
        var itemDescription = document.getElementById('itemDescription').value;

        console.log(itemName, itemPrice,itemQuantity, itemDescription);
        var newItem = {
            name: itemName,
            price: parseFloat(itemPrice),
            quantity: parseInt(itemQuantity),
            description: itemDescription
        };
        fetch('http://localhost:5454/AddGood', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newItem)
        })
            .then(function(response) {
                console.log(response);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
            });
        $('#successMessage').addClass('show');
        setTimeout(function() {
            $('#successMessage').removeClass('show');
        }, 3000); // Hide after 3 seconds

        // Fade out the success message after a delay
        setTimeout(function() {
            $('#successMessage').css('opacity', 0);
        }, 2000); // Fade out over 2 seconds, adjust as needed
        $('#addItemModal').modal('hide');


        document.getElementById('addItemForm').reset();

    }

    window.onload = function() {

        currentPage = getParameterByName('page');
        if (currentPage === null || isNaN(currentPage)) {
            currentPage = 1;
        } else {
            currentPage = parseInt(currentPage);
        }
        fetchData();
        document.getElementById('loadMoreButton').addEventListener('click', loadMoreGoods);
        document.getElementById('previousPageButton').addEventListener('click', previousPage);
    };
    function getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }

    // Function to set the current page URL parameter
    function setCurrentPageParam(pageNumber) {
        var url = new URL(window.location.href);
        url.searchParams.set('page', pageNumber);
        window.history.replaceState({}, '', url);
    }
</script>
</body>
</html>