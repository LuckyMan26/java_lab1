

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
    function displayGoodDetails(good) {
    var goodDetailsElement = document.getElementById('goodDetails');
    var good = {
    name: good.name,
    price: good.price,
    description: good.description,
    imageSrc: "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D"
};

    goodDetailsElement.querySelector('img').src = good.imageSrc;
    goodDetailsElement.querySelector('h2').textContent = good.name;
    goodDetailsElement.querySelector('p:nth-of-type(1)').textContent = 'Price: $' + good.price;
    goodDetailsElement.querySelector('p:nth-of-type(2)').textContent = 'Description: ' + good.description;
    goodDetailsElement.querySelector('button').onclick = addToCart(good.name, good.price);
    goodDetailsElement.style.display='block';
    document.getElementById("container").style.display='none';


}
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
    div.addEventListener('click', function(event) {
    // Prevent the click event from bubbling up to parent elements
    event.stopPropagation();
    // Display the clicked good
    displayGoodDetails(good);
});
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
    function setCurrentGoodParam(id) {
    var url = new URL(window.location.href);
    url.searchParams.set('good_id', id);
    window.history.replaceState({}, '', url);
}
