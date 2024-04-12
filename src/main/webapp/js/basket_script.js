var cartItemCount = 0;
var cartItems = [];
async function fetchBasket(){
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
    console.log("fetch basket finished");
    console.log(cartItemCount);

}

function addToCart(product) {
    cartItemCount++;
    cartItems.push({ name: product.name, price: product.price,product_id: product.good_id });
    document.getElementById('cartItemCount').textContent = cartItemCount;
    console.log('Added ' + product.name + ' to cart. Price: $' + product.price);
    const data = {
        'product_id': product_id,
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