function showOrdersHistory() {

    document.getElementById('itemsInCart').style.display = 'none';
    document.getElementById('home').style.display = 'none';
    document.getElementById('product-details').style.display = 'none';
    document.getElementById('history-of-orders').style.display = 'block';
    fetch('/FetchOrders')
        .then(response =>  response.json())
        .then(data => {
            displayOrderHistory(data);
        })
        .catch(error => console.error('Error:', error));
}

function displayOrderHistory(orders){
    document.getElementById('itemsInCartContainer').innerHTML = '';

    orders.forEach(order => {
        let data = {
            products: order.products
        }
        console.log(data);
        fetch('/GetGoodById', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // Set the content type based on your data format
            },
            body: JSON.stringify(data) // Convert your data to JSON format
        })
            .then(response => response.json()) // Parse the JSON response
            .then(data => {
                console.log(data);
                data.forEach(element => {
                    displayOneProduct(element);
                })


            })
            .catch(error => {
                // Handle any errors
                console.error('Error:', error);
            });
        console.log(order);

    });
}

function displayOneProduct(product){
    const productDiv = document.createElement('div');
    productDiv.classList.add('product');


    const image = document.createElement('img');
    image.src = "data:image/jpeg;base64," + product.imageData ;
    image.alt = product.name;
    image.classList.add('product-image');
    productDiv.appendChild(image);
    const productName = document.createElement('span');
    productName.textContent = 'Product Name: ' + product.name;
    productDiv.appendChild(productName);
    console.log('name: ' + product.name);
    const price = document.createElement('span');
    price.textContent = 'Price: $' + product.price;
    productDiv.appendChild(price);


    document.getElementById('history-of-orders').appendChild(productDiv);
}