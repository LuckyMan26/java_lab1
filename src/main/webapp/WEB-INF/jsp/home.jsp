<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Goods</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">


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
                    <div class="form-group" id="fileDropArea">
                        <label for="fileUploader">Drag & Drop or Click to Upload File</label>
                        <input type="file" class="form-control-file" id="fileUploader">
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
<div class="product-details" id="goodDetails" style="display: none">
    <img src="" alt="No image">
    <div class="text">
        <h2></h2>
        <p></p>
        <p></p>
        <button class="add-to-cart-button">Add to Cart</button>
    </div>
</div>

<div id="successMessage" class="alert alert-success fade" role="alert">
    Item successfully added!
</div>
<footer class="footer">

        <div class="row">
            <div class="col-md-6">
                <h5>Contact Information</h5>
                <p>Email: example@example.com</p>
                <p>Phone: +1234567890</p>
                <p>Address: 123 Main St, City, Country</p>
            </div>
            <div class="col-md-6">
                <h5>Follow Us</h5>
                <ul class="list-inline social-icons">
                    <li class="list-inline-item"><a href="https://www.facebook.com/Amazon/?locale=ru_RU"><i class="bi bi-facebook"></i></a></li>
                    <li class="list-inline-item"><a href="https://twitter.com/amazon"><i class="bi bi-twitter"></i></a></li>
                    <li class="list-inline-item"><a href="https://www.instagram.com/amazon/"><i class="bi bi-instagram"></i></a></li>
                </ul>
            </div>

    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.js" type="text/javascript"></script>

</body>
</html>