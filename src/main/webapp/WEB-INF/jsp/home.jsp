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

<%@ include file="header.jspf" %>


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


<div id="successMessage" class="alert alert-success fade" role="alert">
    Item successfully added!
</div>

<%@ include file="addItemModal.jspf" %>
<%@ include file="product-details.jspf" %>



<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.js" type="text/javascript"></script>

</body>
</html>