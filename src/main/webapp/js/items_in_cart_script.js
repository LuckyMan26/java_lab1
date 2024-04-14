function showBasketItems(){
    document.getElementById("itemsInCart").style.display = 'block';
    document.getElementById("product-details").style.display='none';
    document.getElementById("home").style.display='none';
    console.log("showBasketItems");
    displayitems(cartItems);
    console.log(cartItems);
}

function displayitems(data){
    document.getElementById('itemsInCartContainer').innerHTML = '';
    data.forEach(item => {
        console.log(item);
        const productDiv = document.createElement('div');
        productDiv.classList.add('product');


        const image = document.createElement('img');
        image.src = "data:image/jpeg;base64," + item.imageData ;
        image.alt = item.name;
        image.classList.add('product-image');
        productDiv.appendChild(image);
        const productName = document.createElement('span');
        productName.textContent = 'Product Name: ' + item.name;
        productDiv.appendChild(productName);
        console.log('name: ' + item.name);
        const price = document.createElement('span');
        price.textContent = 'Price: $' + item.price;
        productDiv.appendChild(price);

        const quantity = document.createElement('span');
        quantity.textContent = 'Quantity: ' + item.quantity_available;
        productDiv.appendChild(quantity);

        document.getElementById('itemsInCartContainer').appendChild(productDiv);
    });
}
function buy() {
    // Add your logic for buying here
    let currentDate = new Date();
    const data = {

        products: cartItems,
        date: currentDate,
        client_id: 16,
    };

    fetch('/MakeOrder', {
        method: 'POST',

        body: JSON.stringify(data)
    })
        .then(function(response) {

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        });
    alert("Buying functionality to be implemented!");
}