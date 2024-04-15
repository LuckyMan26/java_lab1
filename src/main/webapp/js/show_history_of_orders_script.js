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

    const historyContainer = document.getElementById('history-of-orders-container');
    historyContainer.innerHTML = '';

    orders.forEach(order => {
        const orderDiv = document.createElement('div');
        orderDiv.classList.add('order');

        const orderInfo = document.createElement('div');
        orderInfo.textContent = `Order ID: ${order.orderId}, Date: ${order.order_date}, Status: ${order.status}`;
        const h1 = document.createElement('p');
        h1.textContent = `Total price: ${order.total_price}`;
        orderDiv.appendChild(orderInfo);
        orderDiv.appendChild(h1);
        const productsContainer = document.createElement('div');
        productsContainer.classList.add('products-container');
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
                    displayOneProduct(element,productsContainer);
                })


            })
            .catch(error => {
                // Handle any errors
                console.error('Error:', error);
            });
        orderDiv.appendChild(productsContainer);
        historyContainer.appendChild(orderDiv);
        console.log(order);

    });
}

function displayOneProduct(product, container){
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

    const price = document.createElement('span');
    price.textContent = 'Price: $' + product.price;
    productDiv.appendChild(price);

    container.appendChild(productDiv);
}