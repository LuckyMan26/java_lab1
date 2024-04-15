var cartItemCount = 0;
var cartItems = [];
 function fetchBasket(){
    console.log("fetchBasket");
    fetch('/FetchBasket')
        .then(response =>  response.json())
        .then(data => {

            cartItems = data;
            cartItemCount = data.length;
            console.log(data.length);
            document.getElementById('cartItemCount').textContent = cartItemCount;
        })
        .catch(error => console.error('Error:', error));
    //console.log("fetch basket finished");
    //console.log(cartItemCount);


}

function addToCart(product) {
    cartItemCount++;
    console.log(cartItemCount);
    cartItems.push({ name: product.name, price: product.price,product_id: product.good_id });
    document.getElementById('cartItemCount').textContent = cartItemCount;
    console.log('Added ' + product.name + ' to cart. Price: $' + product.price);
    const data = {
        'product_id': product.product_id,
        'client_id': 16
    };
    fetch('/AddItemToBasket', {
        method: 'POST',

        body: JSON.stringify(data)
    })
        .then(function(response) {
            console.log(response);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        });

}

function displayBasketItems() {
    var basketItemsContainer = document.getElementById('basketItemsContainer');
    basketItemsContainer.innerHTML = ''; // Clear previous content

    if (cartItems.length === 0) {
        basketItemsContainer.textContent = 'No items added';
    } else {
        cartItems.forEach(function(item, index) {
            var itemElement = document.createElement('div');
            itemElement.textContent = item.name + ' - $' + item.price;
            // Create remove button using Bootstrap's cross icon
            var removeButton = document.createElement('button');
            removeButton.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-times" viewBox="0 0 16 16"><path d="M3.854 3.146a.5.5 0 0 1 0 .708L8.207 8l-4.353 4.146a.5.5 0 0 1-.708-.708L7.793 8 3.146 3.854a.5.5 0 0 1 0-.708z"/><path d="M12.854 3.146a.5.5 0 0 0-.708 0L8 7.793l-4.146-4.647a.5.5 0 1 0-.708.708L7.293 8l-4.647 4.146a.5.5 0 0 0 .708.708L8 8.707l4.146 4.647a.5.5 0 0 0 .708-.708L8.707 8l4.647-4.146a.5.5 0 0 0 0-.708z"/></svg>';
            removeButton.classList.add('btn', 'remove-button'); // Add Bootstrap button classes
            removeButton.onclick = function() {
                removeItemFromCart(index);
            };
            itemElement.appendChild(removeButton);
            basketItemsContainer.appendChild(itemElement);
        });
    }

    basketItemsContainer.classList.add('show'); // Show the container

    // Add event listener to hide basket items list when mouse moves away from both basket icon and basket items list
    basketItemsContainer.addEventListener('mouseleave', hideBasketItems);
}
function hideBasketItems() {
    var basketItemsContainer = document.getElementById('basketItemsContainer');
    basketItemsContainer.classList.remove('show'); // Hide the container
}

function removeItemFromCart(index) {
    const data = {
        'product_id': cartItems[index].product_id,
        'client_id': 16
    };
     //console.log('removeItemFromCart');
    // Remove item from cartItems array
    cartItems.splice(index, 1);
    // Update cartItemCount
    cartItemCount--;
    document.getElementById('cartItemCount').textContent = cartItemCount;
    // Display updated basket items
    displayBasketItems();

    fetch('/RemoveItemFromBasket', {
        method: 'POST',

        body: JSON.stringify(data)
    })
        .then(function(response) {
            //console.log(response);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        });
}