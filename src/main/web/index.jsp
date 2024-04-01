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
            top:20px;
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



        .good-details {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            max-width: 600px;
            margin: auto;
            display: grid;
            grid-template-columns: 1fr 1fr; /* Two columns with equal width */
            grid-gap: 20px; /* Gap between grid items */

        }
        .good-details img {
            width: 100%; /* Image takes full width of its container */
            border-radius: 5px;

        }
        .good-details .text {
            display: flex;
            flex-direction: column;
        }
        .good-details h2 {
            margin-top: 0;
        }
        .good-details p {
            margin: 0;
        }
        header {
            background-color: #ffffff;
            color: #333333;
            padding: 20px;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            z-index: 1000;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .logo {
            text-align: center;
            flex-grow: 1; /* Allow logo to grow and take up available space */
        }

        .logo img {
            height: 80px;
        }

        .icon-container {
            display: flex;
            align-items: center;
            z-index: 999;
        }

        .basket-icon {
            margin-right: 20px; /* Adjust the spacing between the basket icon and the add item button */
        }
        .main-content{
            padding-top: 180px;
        }
    </style>
</head>
<body>






<header>
    <!-- Logo or website name -->
    <div class="logo">
        <img src="https://img.freepik.com/free-vector/bird-colorful-logo-gradient-vector_343694-1365.jpg?size=338&ext=jpg&ga=GA1.1.523418798.1711843200&semt=ais" alt="Logo">
        <!-- If using text instead of an image, use <h1> or <p> -->
    </div>



    <!-- Icon Container -->
    <div class="icon-container">
        <!-- Basket Icon -->
        <div class="basket-icon" onmouseover="displayBasketItems()">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-shopping-cart">
                <circle cx="9" cy="21" r="1"></circle>
                <circle cx="20" cy="21" r="1"></circle>
                <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
            </svg>
            <span class="badge badge-pill badge-primary" id="cartItemCount">0</span>
        </div>
        <div id="basketItemsContainer" class="basket-items-container">
            <!-- Basket items will be displayed here -->
        </div>
        <!-- Add Item Button -->
        <div class="additem-icon">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addItemModal">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                    <path d="M8 1a.5.5 0 0 1 .5.5v13a.5.5 0 0 1-1 0v-13A.5.5 0 0 1 8 1zm7 7a.5.5 0 0 1-.5.5H2a.5.5 0 0 1 0-1h13a.5.5 0 0 1 .5.5z"/>
                </svg>
            </button>
        </div>
    </div>
</header>
<div class="main-content">
<div class="container" id="container">
    <h1>List of Goods</h1>
    <div class="goods-grid" id="goodsGrid"></div>
    <div class="pagination-container mt-3">
        <button id="previousPageButton" class="btn btn-secondary mr-2" disabled>Previous Page</button>
        <button id="loadMoreButton" class="btn btn-primary">Load More</button>
    </div>
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
<div class="good-details" id="goodDetails" style="display: none">
    <img src="https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D" alt="No Image">
    <div class="text">
        <h2>Good Name</h2>
        <p>Price: $50</p>
        <p>Description: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut imperdiet sem eu quam accumsan, in mollis arcu aliquam.</p>
        <button class="add-to-cart-button">Add to Cart</button>
    </div>
</div>

<div id="successMessage" class="alert alert-success fade" role="alert">
    Item successfully added!
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="javascript/script.js"></script>

</body>
</html>