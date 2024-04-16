function getAllOrders(){

    hideAllFragments("orders");
    fetch('/GetAllOrders')
        .then(response =>  response.json())
        .then(data => {
            console.log(data);
            displayOrderHistory(data);
        })
        .catch(error => console.error('Error:', error));
}

function displayOrderHistory(orders){

    const historyContainer = document.getElementById('orders');
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

        // Select element for changing status
        const statusSelect = document.createElement('select');


        // Options for status
        const statusOptions = ["Pending", "Processing", "Shipping", "Delivered"];
        statusOptions.forEach(option => {
            const optionElem = document.createElement('option');
            optionElem.value = option;
            optionElem.textContent = option.charAt(0).toUpperCase() + option.slice(1);
            statusSelect.appendChild(optionElem);
        });
        const changeStatusBtn = document.createElement('button');
        changeStatusBtn.textContent = 'Change Status';
        changeStatusBtn.addEventListener('click', () => {
            // Assuming you have a function to change the status
            changeOrderStatus(order.orderId, statusSelect.value);
        });
        orderDiv.appendChild(changeStatusBtn);
        // Set the selected option to the current status
        statusSelect.value = order.status;

        orderDiv.appendChild(statusSelect);

        const productsContainer = document.createElement('div');
        productsContainer.classList.add('products-container');
        let data = {
            products: order.products
        }

        fetch('/GetGoodById', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // Set the content type based on your data format
            },
            body: JSON.stringify(data) // Convert your data to JSON format
        })
            .then(response => response.json()) // Parse the JSON response
            .then(data => {
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
    });
}
function saveOrderStatus(orderId, status) {
    // Your implementation to save the status goes here
}

// Function to change the status of an order
function changeOrderStatus(orderId, new_status) {
    console.log("changeOrderStatus");
    let data = {
        order_id: orderId,
        status : new_status
    }

    fetch('/ChangeOrderStatus', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    }).catch(error => {
        // Handle any errors
        console.error('Error:', error);
    });
}